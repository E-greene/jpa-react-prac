import React, { useEffect, useRef, useState } from 'react';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import useAuthCheck from './hook/useAuthCheck';

interface ChatResponse {
  senderName: string;
  message: string;
  createDate: string;
}

const ChattingComponent: React.FC = () => {
  const user = useAuthCheck();
  const [roomId, setRoomId] = useState('');
  const [joined, setJoined] = useState(false);
  const [messages, setMessages] = useState<ChatResponse[]>([]);
  const [inputMessage, setInputMessage] = useState('');
  const chatBoxRef = useRef<HTMLDivElement>(null);
  const stompClientRef = useRef<any>(null);

  useEffect(() => {
    if (!joined || !roomId || !user) return;

    const socket = new SockJS('/ws');
    const stompClient = Stomp.over(socket);
    stompClientRef.current = stompClient;

    stompClient.connect({}, () => {
      fetch(`/chats/room/${roomId}`, {
        credentials: 'include',
      })
        .then(res => res.json())
        .then(data => setMessages(data.data));

      stompClient.subscribe(`/topic/chat/${roomId}`, (message: any) => {
        const chatMessage = JSON.parse(message.body);
        setMessages(prev => [...prev, chatMessage]);
      });
    });

    return () => {
      stompClient.disconnect(() => console.log('WebSocket Disconnected'));
    };
  }, [joined, roomId, user]);

  const joinRoom = () => {
    if (roomId.trim() !== '') {
      setMessages([]);
      setJoined(true);
    }
  };

  const sendMessage = () => {
    if (!stompClientRef.current || !user || inputMessage.trim() === '') return;
    stompClientRef.current.send(`/app/chat/${roomId}`, {}, JSON.stringify({ message: inputMessage }));
    setInputMessage('');
  };

  useEffect(() => {
    chatBoxRef.current?.scrollTo(0, chatBoxRef.current.scrollHeight);
  }, [messages]);

  if (!user) return <div>Loading user info...</div>;

  return (
    <div>
      {!joined ? (
        <div>
          <input type="text" placeholder="채팅방 ID 입력" value={roomId} onChange={e => setRoomId(e.target.value)} />
          <button onClick={joinRoom}>입장</button>
        </div>
      ) : (
        <div>
          <h3>채팅방: {roomId}</h3>
          <div ref={chatBoxRef} style={{ border: '1px solid #ccc', height: '300px', overflowY: 'scroll', marginBottom: '10px', padding: '8px' }}>
            {messages.map((msg, idx) => (
              <div key={idx}><strong>{msg.senderName}</strong>: {msg.message}</div>
            ))}
          </div>
          <input type="text" value={inputMessage} onChange={e => setInputMessage(e.target.value)} onKeyDown={e => e.key === 'Enter' && sendMessage()} placeholder="메시지 입력" />
          <button onClick={sendMessage}>전송</button>
        </div>
      )}
    </div>
  );
};

export default ChattingComponent;

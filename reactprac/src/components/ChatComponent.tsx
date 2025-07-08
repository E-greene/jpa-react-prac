// ChatComponent.tsx
import React, { useEffect, useRef } from 'react';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import axios from 'axios';
import useAuthCheck from './hook/useAuthCheck';


let stompClient: any = null; // 타입 충돌 방지

export default function ChatComponent() {
  const user = useAuthCheck();
  
  const messageInput = useRef<HTMLInputElement>(null);

  useEffect(() => {
    const socket = new SockJS('http://localhost:8080/ws'); // 백엔드 연결
    stompClient = Stomp.over(socket);

    stompClient.connect({}, () => {
      console.log('Connected to WebSocket');

      stompClient?.subscribe('/topic/public', (message: any) => {
        const chatMessage = JSON.parse(message.body);
        console.log('Received:', chatMessage);
      });
    });

    return () => {
      stompClient?.disconnect(() => {
        console.log('Disconnected');
      });
    };
  }, []);

  const sendMessage = () => {
    if (stompClient && messageInput.current?.value && user) {
      const message = {
        //sender: user.name,
        content: messageInput.current.value,
      };
      stompClient.send('/app/chat.sendMessage', {}, JSON.stringify(message));
      messageInput.current.value = '';
    }
  };

  if (!user) return <div>Loading user...</div>; // 유저 정보 없으면 렌더링 X

  return (
    <div>
      <input type="text" ref={messageInput} />
      <button onClick={sendMessage}>Send</button>
    </div>
  );
}

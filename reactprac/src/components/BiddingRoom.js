import React, { useEffect, useRef, useState } from 'react';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';

const socketUrl = 'http://localhost:8080/ws';
const topic = '/topic/bid/room1';
const sendEndpoint = 'http://localhost:8080/api/bid';

export default function BiddingRoom() {
  const [bids, setBids] = useState([]);
  const [price, setPrice] = useState('');
  const client = useRef<Client | null>(null);

  useEffect(() => {
    const sock = new SockJS(socketUrl);
    client.current = new Client({
      webSocketFactory: () => sock,
      debug: console.log,
      onConnect: () => {
        client.current?.subscribe(topic, (message) => {
          const bid = JSON.parse(message.body);
          setBids((prev) => [...prev, bid]);
        });
      },
    });
    client.current.activate();

    return () => client.current?.deactivate();
  }, []);

  const handleBid = async () => {
    await fetch(sendEndpoint, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ roomId: 'room1', username: 'user1', price: parseInt(price) }),
    });
    setPrice('');
  };

  return (
    <div>
      <h2>실시간 입찰</h2>
      <input value={price} onChange={(e) => setPrice(e.target.value)} />
      <button onClick={handleBid}>입찰</button>
      <ul>
        {bids.map((b, i) => (
          <li key={i}>{b.username} - {b.price}원</li>
        ))}
      </ul>
    </div>
  );
}

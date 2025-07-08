package com.example.jpaprac.infrastructure.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatRoomManager {

    private final Map<String, Set<WebSocketSession>> chatRooms = new ConcurrentHashMap<>();

    public void addSession(String roomId, WebSocketSession session) {
        chatRooms.computeIfAbsent(roomId, key -> ConcurrentHashMap.newKeySet()).add(session);
    }

    public void removeSession(String roomId, WebSocketSession session) {
        Set<WebSocketSession> sessions = chatRooms.get(roomId);
        if(sessions != null) {
            sessions.remove(session);
        }
    }

    public Set<WebSocketSession> getSessions(String roomId) {
        return chatRooms.getOrDefault(roomId, Collections.emptySet());
    }
}

package com.example.jpaprac.presentation.websocket;

import com.example.jpaprac.application.service.chat.ChatService;
import com.example.jpaprac.infrastructure.websocket.ChatRoomManager;
import com.example.jpaprac.presentation.dto.chat.ChatMessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ChatRoomManager chatRoomManager;
    private final ChatService chatService;

    @Autowired
    public ChatWebSocketHandler(ChatRoomManager chatRoomManager, ChatService chatService) {
        this.chatRoomManager = chatRoomManager;
        this.chatService = chatService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String roomId = getRoomId(session);
        chatRoomManager.addSession(roomId, session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ChatMessageDto dto = objectMapper.readValue(message.getPayload(), ChatMessageDto.class);
        chatService.saveChat(dto);
        String payload = objectMapper.writeValueAsString(dto);
        Set<WebSocketSession> sessions = chatRoomManager.getSessions(dto.getRoomId());
        for (WebSocketSession sess : sessions) {
            if(sess.isOpen()) {
                sess.sendMessage(new TextMessage(payload));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String roomId = getRoomId(session);
        chatRoomManager.removeSession(roomId, session);
    }

    private String getRoomId(WebSocketSession session) {
        return UriComponentsBuilder.fromUri(session.getUri())
                .build()
                .getQueryParams()
                .getFirst("roomId");
    }
}

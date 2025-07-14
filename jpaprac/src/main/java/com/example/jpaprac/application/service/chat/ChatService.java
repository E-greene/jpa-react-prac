package com.example.jpaprac.application.service.chat;

import com.example.jpaprac.domain.entity.Chat;
import com.example.jpaprac.domain.repository.chat.ChatRepository;
import com.example.jpaprac.presentation.dto.chat.ChatMessageDto;
import com.example.jpaprac.presentation.dto.chat.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    //채팅방별 락 객체 저장소
    private final ConcurrentHashMap<String, Object> roomLocks = new ConcurrentHashMap<>();

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public void saveChat(ChatMessageDto dto) {

        //채팅방별 고유 락 객체 생성 또는 가져오기
        Object lock = roomLocks.computeIfAbsent(dto.getRoomId(), key -> new Object());

        synchronized (lock) {
            Chat chat = Chat.create(dto.getRoomId(), dto.getSenderId(), dto.getSenderName(), dto.getMessage());
            chatRepository.save(chat);
        }

    }

    public List<ChatResponse> getChatsByRoomId(String roomId) {
        return chatRepository.findChatsByRoomId(roomId)
                .stream()
                .map(ChatResponse::fromEntity)
                .collect(Collectors.toList());
    }
}

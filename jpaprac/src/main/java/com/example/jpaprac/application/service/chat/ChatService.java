package com.example.jpaprac.application.service.chat;

import com.example.jpaprac.domain.entity.Chat;
import com.example.jpaprac.domain.repository.chat.ChatRepository;
import com.example.jpaprac.presentation.dto.chat.ChatMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public void saveChat(ChatMessageDto dto) {
        Chat chat = Chat.create(dto.getRoomId(), dto.getSenderId(), dto.getSenderName(), dto.getMessage());

        chatRepository.save(chat);
    }

    public List<Chat> getChatsByRoomId(String roomId) {
        return chatRepository.findChatsByRoomId(roomId);
    }
}

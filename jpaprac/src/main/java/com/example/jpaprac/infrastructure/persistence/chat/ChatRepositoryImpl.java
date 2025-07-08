package com.example.jpaprac.infrastructure.persistence.chat;

import com.example.jpaprac.domain.entity.Chat;
import com.example.jpaprac.domain.repository.chat.ChatRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatRepositoryImpl implements ChatRepository {

    private final ChatJpaRepository chatJpaRepository;

    public ChatRepositoryImpl(ChatJpaRepository chatJpaRepository) {
        this.chatJpaRepository = chatJpaRepository;
    }

    @Override
    public List<Chat> findChatsByRoomId(String roomId) {
        return chatJpaRepository.findOrderedByRoomId(roomId);
    }

    @Override
    public Chat save(Chat chat) {
        return chatJpaRepository.save(chat);
    }
}

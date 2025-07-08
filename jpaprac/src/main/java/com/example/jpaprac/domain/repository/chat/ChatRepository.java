package com.example.jpaprac.domain.repository.chat;

import com.example.jpaprac.domain.entity.Chat;

import java.util.List;

public interface ChatRepository {
    List<Chat> findChatsByRoomId(String roomId);
    Chat save(Chat chat);
}

package com.example.jpaprac.presentation.dto.chat;

import com.example.jpaprac.domain.entity.Chat;

import java.time.LocalDateTime;

public class ChatResponse {
    private String senderName;
    private String message;
    private LocalDateTime createDate;

    public ChatResponse() {
    }

    public ChatResponse(String senderName, String message, LocalDateTime createDate) {
        this.senderName = senderName;
        this.message = message;
        this.createDate = createDate;
    }

    public static ChatResponse fromEntity(Chat chat) {
        return new ChatResponse(
                chat.getSenderName(),
                chat.getMessage(),
                chat.getCreatedDate()
        );
    }

    public String getSenderName() {
        return senderName;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }
}

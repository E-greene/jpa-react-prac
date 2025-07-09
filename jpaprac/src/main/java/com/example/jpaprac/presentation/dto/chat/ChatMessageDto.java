package com.example.jpaprac.presentation.dto.chat;

import com.example.jpaprac.presentation.dto.user.UserAuthDto;

public class ChatMessageDto {

    private String roomId;
    private Long senderId;
    private String senderName;
    private String message;

    public ChatMessageDto() {
    }

    public ChatMessageDto(String roomId, Long senderId, String senderName, String message) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.senderName = senderName;
        this.message = message;
    }

    public static ChatMessageDto of(String roomId, Long userId, String userName, String message) {
        return new ChatMessageDto(
                roomId,
                userId,
                userName,
                message
        );
    }

    public String getRoomId() {
        return roomId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getMessage() {
        return message;
    }
}

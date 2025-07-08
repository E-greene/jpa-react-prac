package com.example.jpaprac.presentation.dto.chat;

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

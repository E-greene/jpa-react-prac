package com.example.jpaprac.domain.entity;

import com.example.jpaprac.domain.common.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(name = "chats")
public class Chat extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHAT_SEQ_GENERATOR")
    @SequenceGenerator(name = "CHAT_SEQ_GENERATOR", sequenceName = "CHAT_SEQ", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String roomId;

    @Column(nullable = false)
    private Long senderId;

    @Column(nullable = false)
    private String senderName;

    @Column(nullable = false)
    private String message;

    public Chat() {
    }

    public Long getId() {
        return id;
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

    public static Chat create(String roomId, Long senderId, String senderName, String message) {
        Chat chat = new Chat();
        chat.roomId = roomId;
        chat.senderId = senderId;
        chat.senderName = senderName;
        chat.message = message;
        return chat;
    }
}

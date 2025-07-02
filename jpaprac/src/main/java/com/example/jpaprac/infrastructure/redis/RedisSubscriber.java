package com.example.jpaprac.infrastructure.redis;

import com.example.jpaprac.presentation.controller.auth.AuthController;
import com.example.jpaprac.presentation.dto.bid.BidMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisSubscriber implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(RedisSubscriber.class);

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    public RedisSubscriber(SimpMessagingTemplate messagingTemplate, ObjectMapper objectMapper) {
        this.messagingTemplate = messagingTemplate;
        this.objectMapper = objectMapper;
    }

    public void onMessage(Message message, byte[] pattern){
        try {
            BidMessage bid = objectMapper.readValue(message.toString(), BidMessage.class);
            messagingTemplate.convertAndSend("/topic/bid/" + bid.getRoomId(), bid);
        } catch (Exception e) {
            logger.error("Failed to parse bid message", e);
        }
    }
}

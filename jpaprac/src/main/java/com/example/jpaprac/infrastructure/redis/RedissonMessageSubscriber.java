package com.example.jpaprac.infrastructure.redis;

import com.example.jpaprac.presentation.dto.bid.BidMessage;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedissonMessageSubscriber {

    private final RedissonClient redissonClient;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public RedissonMessageSubscriber(RedissonClient redissonClient, SimpMessagingTemplate messagingTemplate) {
        this.redissonClient = redissonClient;
        this.messagingTemplate = messagingTemplate;

        //모든 방을 구독 (패턴지원X ->수동 구독 필요) Redisson은 PatternTopic을 지원하지 않기 떄문에 구독할 채널 직접 지정
        subscribe("room123");
    }

    public void subscribe(String roomId) {
        String topic = "bid:room:" + roomId;

        redissonClient.getTopic(topic)
                .addListener(BidMessage.class, (channel, msg) -> {
                    messagingTemplate.convertAndSend("/topic/bid/" + roomId, msg);
                });
    }
}

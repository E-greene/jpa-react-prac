package com.example.jpaprac.infrastructure.redis;

import com.example.jpaprac.infrastructure.persistence.redis.BidMessagePublisher;
import com.example.jpaprac.presentation.dto.bid.BidMessage;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedissonBidMessagePublisher implements BidMessagePublisher {
    
    private final RedissonClient redissonClient;

    @Autowired
    public RedissonBidMessagePublisher(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }
    
    public void publish(BidMessage message) {
        String topic = "bid:room:" + message.getRoomId();
        RTopic rTopic = redissonClient.getTopic(topic);
        rTopic.publish(message); // 직렬화는 Jackson 기반
    }
}

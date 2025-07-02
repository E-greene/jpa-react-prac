package com.example.jpaprac.infrastructure.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisPublisher {

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisPublisher(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void publish(String topic, String message) {
        stringRedisTemplate.convertAndSend(topic, message);
    }
}

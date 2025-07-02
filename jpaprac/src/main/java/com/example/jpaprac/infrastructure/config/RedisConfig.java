package com.example.jpaprac.infrastructure.config;

import com.example.jpaprac.infrastructure.redis.RedisSubscriber;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class RedisConfig {

    private final RedisSubscriber redisSubscriber;

    public RedisConfig(RedisSubscriber redisSubscriber) {
        this.redisSubscriber = redisSubscriber;
    }

    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(redisSubscriber, new ChannelTopic("bidding"));
        return container;
    }
}

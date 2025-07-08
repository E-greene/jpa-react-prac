package com.example.jpaprac.infrastructure.config;

import com.example.jpaprac.infrastructure.config.interceptor.HttpHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") //프론트에서 연결할 endpoint
                .addInterceptors(new HttpHandshakeInterceptor()) //웹소켓 연결 시 인증 정보 세션에서 가져옴
                .setAllowedOriginPatterns("*") // 전체 허용
                .withSockJS() //SockJS fallback
                .setSessionCookieNeeded(true); //쿠키 세션 공유 허용
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // 구독 prefix
        registry.setApplicationDestinationPrefixes("/app"); // 메시지 전송 prefix
    }
}

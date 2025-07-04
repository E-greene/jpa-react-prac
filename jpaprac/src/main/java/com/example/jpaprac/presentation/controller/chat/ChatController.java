package com.example.jpaprac.presentation.controller.chat;

import com.example.jpaprac.domain.entity.User;
import com.example.jpaprac.presentation.dto.chat.ChatMessage;
import com.example.jpaprac.presentation.dto.user.UserAuthDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ChatController {
    
    @MessageMapping("/chat.sendMessage") // /app/chat.sendMessage로 온 메시지 처리
    @SendTo("/topic/public") // 결과를 /topic/public으로 브로드캐스트
    public ChatMessage sendMessage(ChatMessage message, @AuthenticationPrincipal UserAuthDto userAuthDto) {
        String loginId = userAuthDto.getLoginId();
        return new ChatMessage(loginId, message.getContent());
    }
}

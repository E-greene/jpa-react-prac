package com.example.jpaprac.presentation.controller.chat;

import com.example.jpaprac.application.service.chat.ChatService;
import com.example.jpaprac.common.ApiResponse;
import com.example.jpaprac.domain.entity.User;
import com.example.jpaprac.presentation.dto.chat.ChatMessage;
import com.example.jpaprac.presentation.dto.chat.ChatMessageDto;
import com.example.jpaprac.presentation.dto.chat.ChatMessageRequest;
import com.example.jpaprac.presentation.dto.chat.ChatResponse;
import com.example.jpaprac.presentation.dto.user.UserAuthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chats")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<ApiResponse<List<ChatResponse>>> getChatsByRoom(@PathVariable String roomId) {
        try {
            List<ChatResponse> responses = chatService.getChatsByRoomId(roomId);
            return ResponseEntity.ok(ApiResponse.success("채팅 내역 조회 성공", responses));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("채팅 내역 조회 실패"));
        }
    }

    @MessageMapping("/chat.sendMessage") // /app/chat.sendMessage로 온 메시지 처리
    @SendTo("/topic/public") // 결과를 /topic/public으로 브로드캐스트
    public ChatMessage sendMessage(ChatMessage message, Principal principal) {
        String userName = principal != null ? principal.getName() : "anonymous";
        return new ChatMessage(userName, message.getContent());
    }

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/chat/{roomId}")
    public ChatResponse handleChatMessage(@DestinationVariable String roomId, ChatMessageRequest messageRequest, Principal principal) {

        UserAuthDto userAuthDto = (UserAuthDto) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        ChatMessageDto message = ChatMessageDto.of(roomId, userAuthDto.getId(), userAuthDto.getName(), messageRequest.getMessage());

        chatService.saveChat(message);

        return new ChatResponse(userAuthDto.getName(), message.getMessage(), LocalDateTime.now());
    }
}

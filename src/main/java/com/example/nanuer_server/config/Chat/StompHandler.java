package com.example.nanuer_server.config.Chat;


import com.example.nanuer_server.config.User.JwtTokenProvider;
import com.example.nanuer_server.domain.entity.ChatMessageEntity;
import com.example.nanuer_server.domain.repository.UserRepository;
import com.example.nanuer_server.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {
    private final JwtTokenProvider jwtTokenProvider;
    //private final ChatService chatService;
    private final UserRepository userRepository;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        System.out.println("message:" + message);
        System.out.println("헤더 : " + message.getHeaders());

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            jwtTokenProvider.validateToken(Objects.requireNonNull(accessor.getFirstNativeHeader("X-AUTH-TOKEN")));
            System.out.println("토큰 : " + accessor.getNativeHeader("X-AUTH-TOKEN"));
        }
        return message;
    }
}

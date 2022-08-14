package com.example.nanuer_server.domain.repository.Chat;

import com.example.nanuer_server.domain.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity,Integer> {
    Optional<ChatRoomEntity> findByRoomId(int roomId);
}

package com.example.jpaprac.infrastructure.persistence.chat;

import com.example.jpaprac.domain.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatJpaRepository extends JpaRepository<Chat, Long> {

    @Query("SELECT c FROM Chat c WHERE c.roomId = :roomId ORDER BY c.createdDate ASC")
    List<Chat> findOrderedByRoomId(@Param("roomId") String roomId);
}

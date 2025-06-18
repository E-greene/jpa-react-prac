package com.example.jpaprac.infrastructure.persistence.board;

import com.example.jpaprac.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardJpaRepository extends JpaRepository<Board, Long> {
    List<Board> findByUserIdAndDeleteYN(Long userId, String deleteYN);
    List<Board> findAllByDeleteYN(String deleteYN);
    Optional<Board> findByIdAndDeleteYN(Long id, String deleteYN);
}

package com.example.jpaprac.domain.repository.board;

import com.example.jpaprac.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByUserId(Long userId);
}

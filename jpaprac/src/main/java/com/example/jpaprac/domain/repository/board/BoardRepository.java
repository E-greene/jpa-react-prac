package com.example.jpaprac.domain.repository.board;

import com.example.jpaprac.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository {
    List<Board> findAll();
    List<Board> findByUserId(Long userId);
    Board findById(Long boardId);
    Board save(Board board);
    void deleteById(Long boardId);

    List<Board> findAllWithUser();

    Board findByIdWithUser(Long boardId);
}

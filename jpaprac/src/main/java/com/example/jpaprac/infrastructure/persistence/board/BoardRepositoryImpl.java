package com.example.jpaprac.infrastructure.persistence.board;

import com.example.jpaprac.domain.entity.Board;
import com.example.jpaprac.domain.repository.board.BoardRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepositoryImpl implements BoardRepository {

    private final BoardJpaRepository boardJpaRepository;

    public BoardRepositoryImpl(BoardJpaRepository boardJpaRepository) {
        this.boardJpaRepository = boardJpaRepository;
    }

    @Override
    public List<Board> findAll() {
        return boardJpaRepository.findAllByDeleteYN("N");
    }

    public Board findById(Long id) {
        return boardJpaRepository.findByIdAndDeleteYN(id, "N").orElse(null);
    }

    public List<Board> findByUserId(Long userId) {
        return boardJpaRepository.findByUserIdAndDeleteYN(userId, "N");
    }

    public Board save(Board board) {
        return boardJpaRepository.save(board);
    }

    public void deleteById(Long id) {
        boardJpaRepository.deleteById(id);
    }
}
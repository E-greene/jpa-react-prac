package com.example.jpaprac.infrastructure.persistence.board;

import com.example.jpaprac.domain.entity.Board;
import com.example.jpaprac.domain.repository.board.BoardRepository;

import java.util.List;

public class BoardRepositoryImpl implements BoardRepository {

    private final BoardJpaRepository boardJpaRepository;

    public BoardRepositoryImpl(BoardJpaRepository boardJpaRepository) {
        this.boardJpaRepository = boardJpaRepository;
    }

    @Override
    public List<Board> findAll() {
        return boardJpaRepository.findAll();
    }

    public Board findById(Long id) {
        return boardJpaRepository.findById(id);
    }

    public List<Board> findByUserId(Long userId) {
        return boardJpaRepository.findByUserId(userId);
    }

    public Board save(Board board) {
        return boardJpaRepository.save(board);
    }

    public void deleteById(Long id) {
        boardJpaRepository.deleteById(id);
    }
}
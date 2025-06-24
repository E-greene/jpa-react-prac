package com.example.jpaprac.infrastructure.persistence.board;

import com.example.jpaprac.domain.entity.Board;
import com.example.jpaprac.domain.repository.board.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepositoryImpl implements BoardRepository {

    private final BoardJpaRepository boardJpaRepository;

    @Autowired
    public BoardRepositoryImpl(BoardJpaRepository boardJpaRepository) {
        this.boardJpaRepository = boardJpaRepository;
    }

    @Override
    public List<Board> findAll() {
        return boardJpaRepository.findAllByDeleteYN("N");
    }

    @Override
    public Board findById(Long id) {
        return boardJpaRepository.findByIdAndDeleteYN(id, "N").orElse(null);
    }

    @Override
    public List<Board> findByUserId(Long userId) {
        return boardJpaRepository.findByUserIdAndDeleteYN(userId, "N");
    }

    @Override
    public Board save(Board board) {
        return boardJpaRepository.save(board);
    }

    @Override
    public void deleteById(Long id) {
        boardJpaRepository.deleteById(id);
    }

    @Override
    public List<Board> findAllWithUser() {
        return boardJpaRepository.findAllWithUser("N");
    }

    @Override
    public Board findByIdWithUser(Long boardId) {
        return boardJpaRepository.findByIdWithUser(boardId, "N").orElse(null);
    }
}
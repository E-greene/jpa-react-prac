package com.example.jpaprac.service;

import com.example.jpaprac.domain.entity.Board;
import com.example.jpaprac.model.BoardDto;
import com.example.jpaprac.domain.reposirory.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional(readOnly = true)
    public List<BoardDto> findAll() {
        return boardRepository.findAll().stream()
                .map(BoardDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<BoardDto> findBoardsByUserId(Long userId) {
        List<Board> boards = boardRepository.findByUserId(userId);
        return boards.stream()
                .map(BoardDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BoardDto findById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not Found"));
        return BoardDto.fromEntity(board);
    }

    @Transactional
    public Long saveBoardById(BoardDto dto) {
        Board savedBoard = boardRepository.save(BoardDto.toEntity(dto));
        return BoardDto.fromEntity(savedBoard).getId();
    }

    @Transactional
    public Long updateBoardById(Long id, BoardDto dto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Board not Found"));

        board.update(dto.getTitle(), dto.getContent());

        return id;
    }

    @Transactional
    public void deleteBoardById(Long id) {
        boardRepository.deleteById(id);
    }
}

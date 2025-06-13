package com.example.jpaprac.presentation.controller;

import com.example.jpaprac.presentation.model.BoardDto;
import com.example.jpaprac.application.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public List<BoardDto> list() {
        return boardService.findAll();
    }

    @GetMapping("/{userId}")
    public List<BoardDto> findByUserId(@PathVariable Long userId) {
        return boardService.findBoardsByUserId(userId);
    }

    @GetMapping("/{id}")
    public BoardDto findById(@PathVariable Long id) {
        return boardService.findById(id);
    }

    @PostMapping("/save")
    public Long save(@RequestBody BoardDto dto) {
        return boardService.saveBoardById(dto);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardDto dto) {
        return boardService.updateBoardById(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        boardService.deleteBoardById(id);
    }
}

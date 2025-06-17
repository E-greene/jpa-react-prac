package com.example.jpaprac.presentation.controller.board;

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
    
    //전체게시글 목록 조회
    @GetMapping
    public List<BoardDto> getAllBoards() {
        return boardService.findAll();
    }
    
    //게시글 단건 조회
    @GetMapping("/{boardId}")
    public BoardDto getBoardById(@PathVariable Long boardId) {
        return boardService.findById(boardId);
    }
    
    //게시글 생성
    @PostMapping
    public Long createBoard(@RequestBody BoardDto dto) {
        return boardService.saveBoard(dto);
    }
    
    //게시글 수정
    @PutMapping("/{boardId}")
    public BoardDto updateBoard(@PathVariable Long boardId, @RequestBody BoardDto dto) {
        return boardService.updateBoardById(boardId, dto);
    }
    
    //게시글 삭제
    @DeleteMapping("/{boardId}")
    public void deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoardById(boardId);
    }


}

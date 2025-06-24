package com.example.jpaprac.presentation.controller.board;

import com.example.jpaprac.presentation.dto.board.*;
import com.example.jpaprac.application.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<BoardResponse> getAllBoards() {
        return boardService.findAllWithUser()
                .stream()
                .map(BoardResponse::fromBoardApplicationDto)
                .collect(Collectors.toList());
    }
    
    //게시글 단건 조회
    @GetMapping("/{boardId}")
    public BoardResponse getBoardById(@PathVariable Long boardId) {
        BoardApplicationDto boardApplicationDto = boardService.findByIdWithUser(boardId);
        return BoardResponse.fromBoardApplicationDto(boardApplicationDto);
    }
    
    //게시글 생성
    @PostMapping
    public Long createBoard(@RequestBody CreateBoardRequest createBoardRequest) {
        CreateBoardCommand createBoardCommand = CreateBoardCommand.fromCreateBoardRequest(createBoardRequest);
        return boardService.saveBoard(createBoardCommand);
    }


    
    //게시글 수정
    @PutMapping("/{boardId}")
    public BoardResponse updateBoard(@PathVariable Long boardId, @RequestBody UpdateBoardRequest updateBoardRequest) {
        UpdateBoardCommand updateBoardCommand = UpdateBoardCommand.fromUpdateBoardRequest(updateBoardRequest);
        BoardApplicationDto boardApplicationDto = boardService.updateBoardById(boardId, updateBoardCommand);
        return BoardResponse.fromBoardApplicationDto(boardApplicationDto);
    }
    
    //게시글 삭제
    @DeleteMapping("/{boardId}")
    public void deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoardById(boardId);
    }

}

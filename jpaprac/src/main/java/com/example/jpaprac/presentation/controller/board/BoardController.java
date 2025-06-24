package com.example.jpaprac.presentation.controller.board;

import com.example.jpaprac.common.ApiResponse;
import com.example.jpaprac.presentation.dto.board.*;
import com.example.jpaprac.application.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<List<BoardResponse>>> getBoards() {
        try {
            List<BoardResponse> response = boardService.findAllWithUser()
                    .stream()
                    .map(BoardResponse::fromBoardApplicationDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(ApiResponse.success("게시글 목록 조회 성공!", response));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("게시글 목록 조회 중 오류 발생"));
        }
    }
    
    //게시글 단건 조회
    @GetMapping("/{boardId}")
    public ResponseEntity<ApiResponse<BoardResponse>> getBoardById(@PathVariable Long boardId) {
        try {
            BoardApplicationDto boardApplicationDto = boardService.findByIdWithUser(boardId);
            BoardResponse response = BoardResponse.fromBoardApplicationDto(boardApplicationDto);
            return ResponseEntity.ok(ApiResponse.success("게시글 조회 성공", response));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("게시글 조회 중 오류 발생"));
        }


    }
    
    //게시글 생성
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createBoard(@RequestBody CreateBoardRequest createBoardRequest) {
        try {
            CreateBoardCommand createBoardCommand = CreateBoardCommand.fromCreateBoardRequest(createBoardRequest);
            Long id = boardService.saveBoard(createBoardCommand);
            return ResponseEntity.ok(ApiResponse.success("게시글 작성 성공", id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("게시글 작성 중 오류 발생"));
        }

    }


    
    //게시글 수정
    @PutMapping("/{boardId}")
    public ResponseEntity<ApiResponse<BoardResponse>> updateBoard(@PathVariable Long boardId, @RequestBody UpdateBoardRequest updateBoardRequest) {
        try {
            UpdateBoardCommand updateBoardCommand = UpdateBoardCommand.fromUpdateBoardRequest(updateBoardRequest);
            BoardApplicationDto boardApplicationDto = boardService.updateBoardById(boardId, updateBoardCommand);
            BoardResponse response = BoardResponse.fromBoardApplicationDto(boardApplicationDto);

            return ResponseEntity.ok(ApiResponse.success("게시글 수정 성공", response));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("게시글 수정 중 오류 발생"));
        }

    }
    
    //게시글 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<ApiResponse<Void>> deleteBoard(@PathVariable Long boardId) {
        try {
            boardService.deleteBoardById(boardId);
            return ResponseEntity.ok(ApiResponse.success("게시글 삭제 성공"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("게시글 삭제 중 오류 발생"));
        }

    }

}

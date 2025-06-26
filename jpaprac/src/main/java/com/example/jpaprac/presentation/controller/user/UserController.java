package com.example.jpaprac.presentation.controller.user;

import com.example.jpaprac.application.service.board.BoardService;
import com.example.jpaprac.application.service.user.UserService;
import com.example.jpaprac.common.ApiResponse;
import com.example.jpaprac.presentation.dto.board.BoardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final BoardService boardService;

    @Autowired
    public UserController(UserService userService, BoardService boardService) {
        this.userService = userService;
        this.boardService = boardService;
    }

    @GetMapping("/{userId}/boards")
    public ResponseEntity<ApiResponse<List<BoardResponse>>> findByUserId(@PathVariable Long userId) {

        try {
            List<BoardResponse> result = boardService.findBoardsByUserId(userId)
                    .stream()
                    .map(BoardResponse::fromBoardApplicationDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(ApiResponse.success("사용자 게시글 조회 성공", result));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("사용자 게시글 조회 중 오류 발생"));
        }
    }



}

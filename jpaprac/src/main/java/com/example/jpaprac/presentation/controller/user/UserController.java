package com.example.jpaprac.presentation.controller.user;

import com.example.jpaprac.application.service.board.BoardService;
import com.example.jpaprac.application.service.user.UserService;
import com.example.jpaprac.presentation.dto.board.BoardResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<BoardResponse> findByUserId(@PathVariable Long userId) {
        return boardService.findBoardsByUserId(userId)
                .stream()
                .map(BoardResponse::fromBoardApplicationDto)
                .collect(Collectors.toList());
    }

}

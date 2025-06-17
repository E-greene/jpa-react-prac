package com.example.jpaprac.presentation.controller.user;

import com.example.jpaprac.application.service.board.BoardService;
import com.example.jpaprac.presentation.model.BoardDto;
import com.example.jpaprac.presentation.model.UserDto;
import com.example.jpaprac.application.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final BoardService boardService;

    public UserController(UserService userService, BoardService boardService) {
        this.userService = userService;
        this.boardService = boardService;
    }

    @GetMapping("/{userId}/boards")
    public List<BoardDto> findByUserId(@PathVariable Long userId) {
        return boardService.findBoardsByUserId(userId);
    }

}

package com.example.jpaprac.controller;

import com.example.jpaprac.model.UserDto;
import com.example.jpaprac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원가입
    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody UserDto dto) {
        UserDto savedUser = userService.signUp(dto);
        return ResponseEntity.ok(savedUser);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto dto) {
        UserDto loggedInUser =userService.login(dto.getLoginId(), dto.getLoginPwd());
        return ResponseEntity.ok(loggedInUser);
    }

}

package com.example.jpaprac.presentation.controller.auth;

import com.example.jpaprac.application.service.auth.AuthService;
import com.example.jpaprac.presentation.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //회원가입
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody UserDto dto) {
        try {
            UserDto savedUser = authService.signUp(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto dto) {
        UserDto loggedInUser =authService.login(dto.getLoginId(), dto.getLoginPwd());
        return ResponseEntity.ok(loggedInUser);
    }
}

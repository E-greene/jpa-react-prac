package com.example.jpaprac.presentation.controller.auth;

import com.example.jpaprac.application.service.auth.AuthService;
import com.example.jpaprac.common.ApiResponse;
import com.example.jpaprac.presentation.dto.auth.LoginUserCommand;
import com.example.jpaprac.presentation.dto.auth.LoginUserRequest;
import com.example.jpaprac.presentation.dto.user.*;
import org.h2.command.ddl.CreateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auths")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //회원가입
    @PostMapping("/signUp")
    public ResponseEntity<ApiResponse<UserResponse>> signUp(@RequestBody CreateUserRequest createUserRequest) {
        try {
            CreateUserCommand createUserCommand = CreateUserCommand.fromCreateBoardRequest(createUserRequest);

            UserApplicationDto signUpUser = authService.signUp(createUserCommand);
            UserResponse userResponse = UserResponse.fromUserApplicationDto(signUpUser);
            return ResponseEntity.ok(ApiResponse.success("회원가입 성공", userResponse));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("회원가입 중 오류 발생"));
        }

    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponse>> login(@RequestBody LoginUserRequest loginUserRequest) {

        try {
            LoginUserCommand loginUserCommand = LoginUserCommand.fromLoginUserRequest(loginUserRequest);
            UserApplicationDto loggedInUser = authService.login(loginUserCommand);
            UserResponse userResponse = UserResponse.fromUserApplicationDto(loggedInUser);

            return ResponseEntity.ok(ApiResponse.success("로그인 성공", userResponse));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("로그인 중 오류 발생"));
        }
    }
}

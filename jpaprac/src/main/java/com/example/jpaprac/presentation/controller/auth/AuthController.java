package com.example.jpaprac.presentation.controller.auth;

import com.example.jpaprac.application.service.auth.AuthService;
import com.example.jpaprac.application.service.user.UserService;
import com.example.jpaprac.common.ApiResponse;
import com.example.jpaprac.domain.entity.User;
import com.example.jpaprac.infrastructure.config.interceptor.UserDetailsImpl;
import com.example.jpaprac.presentation.dto.auth.LoginResponse;
import com.example.jpaprac.presentation.dto.auth.LoginUserCommand;
import com.example.jpaprac.presentation.dto.auth.LoginUserRequest;
import com.example.jpaprac.presentation.dto.auth.UserInfoResponse;
import com.example.jpaprac.presentation.dto.user.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 인증 관련 컨트롤러 (리액트 API용)
 */
@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthService authService, UserService userService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * 회원가입 API
     */
    @PostMapping("/api/auth/signUp")
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
    @PostMapping("/api/auth/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginUserRequest loginUserRequest) {

        logger.info("로그인 요청: {}", loginUserRequest);
        logger.debug("loginUserRequest: loginId={}, loginPwd={}", loginUserRequest.getLoginId(), loginUserRequest.getLoginPwd());

        try {

            //인증 객체 생성 스프링 시큐리티를 통한 인증 (userId를 username으로 사용)
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginUserRequest.getLoginId(), loginUserRequest.getLoginPwd()
            );

            Authentication authResult = authenticationManager.authenticate(authenticationToken);

            //인증 성공 시 SecurityContext에 저장
            SecurityContextHolder.getContext().setAuthentication(authResult);

            //UserDetailsImpl에서 사용자 정보 추출
            UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();

            LoginResponse loginResponse = new LoginResponse(
                    userDetails.getUsername(),
                    userDetails.getUser().getRole().name()
            );


            logger.info("로그인 성공 loginResponse: {}", loginResponse);

            return ResponseEntity.ok(ApiResponse.success("로그인 성공", loginResponse));
        } catch (IllegalArgumentException e) {
            logger.warn("로그인 실패: {}", e.getMessage());
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            logger.error("로그인 중 오류 발생", e);
            return ResponseEntity.internalServerError().body(ApiResponse.error("로그인 중 오류 발생"));
        }
    }

    // 프론트에서 로그인한 사용자인지 확인 위함
    @GetMapping("/api/auth/me")
    public ResponseEntity<ApiResponse<UserInfoResponse>> getMyInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        if (userDetails == null) {
            return ResponseEntity.status(401).body(ApiResponse.error("인증되지 않은 사용자입니다."));
        }

        try {

            UserInfoResponse userInfoResponse = new UserInfoResponse(
                    userDetails.getUsername(),
                    userDetails.getUser().getRole().name()
            );

            return ResponseEntity.ok(ApiResponse.success("유저 정보 조회 성공", userInfoResponse));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("유저 정보 조회 중 오류 발생"));
        }
    }

    /**
     * 리액트용 로그아웃 API
     */
    @PostMapping("/api/auth/logout")
    public ResponseEntity<ApiResponse<String>> logout(HttpServletRequest request) {
        try {
            //현재 인증 정보 확인
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.isAuthenticated() &&
                !authentication.getPrincipal().equals("anonymousUser")) {

                // 보안 컨텍스트 클리어
                SecurityContextHolder.clearContext();

                //세션 무효화
                if(request.getSession(false) != null) {
                    request.getSession().invalidate();
                }

                return ResponseEntity.ok(ApiResponse.success("로그아웃이 완료되었습니다", null));
            } else {
                return ResponseEntity.status(401).body(ApiResponse.error("로그인되지 않은 사용자입니다."));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("로그아웃 처리 중 오류가 발생했습니다."));
        }
    }
}

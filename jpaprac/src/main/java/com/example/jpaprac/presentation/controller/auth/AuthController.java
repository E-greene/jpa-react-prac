package com.example.jpaprac.presentation.controller.auth;

import com.example.jpaprac.application.service.auth.AuthService;
import com.example.jpaprac.application.service.user.UserService;
import com.example.jpaprac.common.ApiResponse;
import com.example.jpaprac.domain.entity.User;
import com.example.jpaprac.presentation.dto.auth.LoginUserCommand;
import com.example.jpaprac.presentation.dto.auth.LoginUserRequest;
import com.example.jpaprac.presentation.dto.user.*;
import org.h2.command.ddl.CreateUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/auths")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
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
    public ResponseEntity<ApiResponse<UserResponse>> login(@RequestBody LoginUserRequest loginUserRequest,
                                                           HttpServletRequest request) {

        logger.info("로그인 요청: {}", loginUserRequest);
        logger.debug("loginUserRequest: loginId={}, loginPwd={}", loginUserRequest.getLoginId(), loginUserRequest.getLoginPwd());

        try {
            LoginUserCommand loginUserCommand = LoginUserCommand.fromLoginUserRequest(loginUserRequest);
            User user = authService.login(loginUserCommand);

            UserAuthDto userAuthDto = new UserAuthDto(user.getId(), user.getName(), user.getLoginId(), user.getRole());

            //인증 객체 생성
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userAuthDto,
                    null,
                    List.of(new SimpleGrantedAuthority(user.getRole().getRoleName()))
            );

            //SecurityContext에 저장
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            //4. 세션에 SecurityContext 저장(JSSENIONID 쿠키 발급를 위함)
            HttpSession session = request.getSession(true);
            session.setAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext()
            );

            UserApplicationDto loggedInUser = UserApplicationDto.fromEntity(user);

            UserResponse userResponse = UserResponse.fromUserApplicationDto(loggedInUser);

            logger.info("로그인 성공 userResponse: {}", userResponse);

            return ResponseEntity.ok(ApiResponse.success("로그인 성공", userResponse));
        } catch (IllegalArgumentException e) {
            logger.warn("로그인 실패: {}", e.getMessage());
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            logger.error("로그인 중 오류 발생", e);
            return ResponseEntity.internalServerError().body(ApiResponse.error("로그인 중 오류 발생"));
        }
    }

    // 프론트에서 로그인한 사용자인지 확인 위함
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> getMyInfo(@AuthenticationPrincipal UserAuthDto userAuthDto) {
        try {
            UserApplicationDto dto = userService.findById(userAuthDto.getId());
            UserResponse userResponse = UserResponse.fromUserApplicationDto(dto);

            return ResponseEntity.ok(ApiResponse.success("유저 정보 조회 성공", userResponse));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("유저 정보 조회 중 오류 발생"));
        }
    }


}

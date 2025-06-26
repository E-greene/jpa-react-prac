package com.example.jpaprac.application.service.auth;

import com.example.jpaprac.domain.entity.User;
import com.example.jpaprac.domain.repository.auth.AuthRepository;
import com.example.jpaprac.presentation.dto.auth.LoginUserCommand;
import com.example.jpaprac.presentation.dto.user.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //회원가입
    @Transactional
    public UserApplicationDto signUp(CreateUserCommand createUserCommand) {
        User existUser = authRepository.findByLoginId(createUserCommand.getLoginId());
        if(existUser != null) {
            throw new IllegalArgumentException("이미 사용 중인 로그인 ID입니다.");
        }

        //비밀번호 암호화
        String encodedPwd = passwordEncoder.encode(createUserCommand.getLoginPwd());

        User user = User.create(createUserCommand, encodedPwd);
        User savedUser = authRepository.save(user);

        return UserApplicationDto.fromEntity(savedUser);

    }

    //로그인
    @Transactional
    public UserApplicationDto login(LoginUserCommand loginUserCommand) {

        logger.info("서비스 login 호출: {}", loginUserCommand);

        //loginId로 User찾기
        User user = authRepository.findByLoginId(loginUserCommand.getLoginId());
        if (user == null) {
            logger.warn("로그인 실패: 아이디/비밀번호 불일치 loginId={}", loginUserCommand.getLoginId());
            throw new IllegalArgumentException("아이디와 비밀번호를 다시 확인해주세요");
        }

        //비밀번호 비교 (Bcrypt)
        if (!passwordEncoder.matches(loginUserCommand.getLoginPwd(), user.getLoginPwd())) {
            logger.warn("로그인 실패: 비밀번호 불일치 loginId={}", loginUserCommand.getLoginId());
            throw new IllegalArgumentException("아이디와 비밀번호를 다시 확인해주세요");
        }

        logger.info("로그인 성공, User: {}", user);
        return UserApplicationDto.fromEntity(user);
    }
}

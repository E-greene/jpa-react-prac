package com.example.jpaprac.application.service.auth;

import com.example.jpaprac.domain.entity.User;
import com.example.jpaprac.domain.repository.auth.AuthRepository;
import com.example.jpaprac.presentation.dto.auth.LoginUserCommand;
import com.example.jpaprac.presentation.dto.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final AuthRepository authRepository;

    @Autowired
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    //회원가입
    @Transactional
    public UserApplicationDto signUp(CreateUserCommand createUserCommand) {
        User existUser = authRepository.findByLoginId(createUserCommand.getLoginId());
        if(existUser != null) {
            throw new IllegalArgumentException("이미 사용 중인 로그인 ID입니다.");
        }

        User user = User.create(createUserCommand);
        User savedUser = authRepository.save(user);

        return UserApplicationDto.fromEntity(savedUser);

    }

    //로그인
    @Transactional
    public UserApplicationDto login(LoginUserCommand loginUserCommand) {

        User user = authRepository.findByLoginIdAndLoginPwd(loginUserCommand.getLoginId(), loginUserCommand.getLoginPwd());
        if (user == null) {
            throw new IllegalArgumentException("아이디와 비밀번호를 다시 확인해주세요");
        }

        return UserApplicationDto.fromEntity(user);
    }
}

package com.example.jpaprac.application.service.auth;

import com.example.jpaprac.domain.entity.User;
import com.example.jpaprac.domain.repository.auth.AuthRepository;
import com.example.jpaprac.presentation.dto.user.UserDto;
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
    public UserDto signUp(UserDto userDto) {
        User existUser = authRepository.findByLoginId(userDto.getLoginId());
        if(existUser != null) {
            throw new IllegalArgumentException("이미 사용 중인 로그인 ID입니다.");
        }

        User user = userDto.toEntity(userDto);
        User savedUser = authRepository.save(user);

        return UserDto.fromEntity(savedUser);

    }

    //로그인
    @Transactional
    public UserDto login(String loginId, String loginPwd) {
        User user = authRepository.findByLoginIdAndLoginPwd(loginId, loginPwd);
        if (user == null) {
            throw new IllegalArgumentException("아이디와 비밀번호를 다시 확인해주세요");
        }

        return UserDto.fromEntity(user);
    }
}

package com.example.jpaprac.application.service.user;

import com.example.jpaprac.domain.entity.User;
import com.example.jpaprac.presentation.model.UserDto;
import com.example.jpaprac.domain.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //회원가입
    @Transactional
    public UserDto signUp(UserDto userDto) {
        Optional<User> existUser = userRepository.findByLoginId(userDto.getLoginId());
        if(existUser.isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 로그인 ID입니다.");
        }

        User user = userDto.toEntity(userDto);
        User savedUser = userRepository.save(user);

        return UserDto.fromEntity(savedUser);

    }

    //로그인
    @Transactional
    public UserDto login(String loginId, String loginPwd) {
        User user = userRepository.findByLoginIdAndLoginPwd(loginId, loginPwd)
                .orElseThrow(()->new IllegalArgumentException("아이디와 비밀번호를 다시 확인해주세요"));

        return UserDto.fromEntity(user);
    }

}

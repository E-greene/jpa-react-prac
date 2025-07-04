package com.example.jpaprac.application.service.user;

import com.example.jpaprac.domain.common.exception.UserNotFoundException;
import com.example.jpaprac.domain.entity.User;
import com.example.jpaprac.domain.repository.user.UserRepository;
import com.example.jpaprac.presentation.dto.user.UserApplicationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //userId로 사용자정보 조회
    @Transactional
    public UserApplicationDto findById(Long userId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new UserNotFoundException("사용자가 존재하지 않습니다.");
        }
        return UserApplicationDto.fromEntity(user);
    }

}

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



}

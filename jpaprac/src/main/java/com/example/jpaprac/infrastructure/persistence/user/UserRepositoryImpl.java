package com.example.jpaprac.infrastructure.persistence.user;

import com.example.jpaprac.domain.entity.User;
import com.example.jpaprac.domain.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Autowired
    public UserRepositoryImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    public User findById(Long id) {
        return userJpaRepository.findById(id).orElse(null);
    }
}

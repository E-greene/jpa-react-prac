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

    @Override
    public Optional<User> findByLoginId(String loginId) {
        return userJpaRepository.findByLoginId(loginId);
    }

    @Override
    public Optional<User> findByLoginIdAndLoginPwd(String loginId, String loginPwd) {
        return userJpaRepository.findByLoginIdAndLoginPwd(loginId, loginPwd);
    }

    public User save(User dto) {
        return userJpaRepository.save(dto);
    }
}

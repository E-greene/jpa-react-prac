package com.example.jpaprac.infrastructure.persistence.security;

import com.example.jpaprac.domain.entity.User;
import com.example.jpaprac.domain.repository.auth.AuthRepository;
import com.example.jpaprac.domain.repository.user.UserRepository;
import com.example.jpaprac.domain.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Autowired
    public CustomUserDetailsService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = authRepository.findByLoginId(loginId);
        if(user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다 : " + loginId);
        }
        return new CustomUserDetails(user);
    }
}

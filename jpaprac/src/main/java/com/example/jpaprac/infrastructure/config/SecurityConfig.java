package com.example.jpaprac.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                    .antMatchers("/auths/signUp","/auths/login","/ws/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin().disable()
                .logout()
                    .logoutUrl("/auths/logout")
                    .logoutSuccessHandler(((request, response, authentication) -> {
                        response.setStatus(200); // 200 OK로 응답
                    }))
                    .invalidateHttpSession(true) // 세션 무효화
                    .deleteCookies("JSESSIONID") //세션 쿠키 제거
                    .permitAll();
        return http.build();
    }
}

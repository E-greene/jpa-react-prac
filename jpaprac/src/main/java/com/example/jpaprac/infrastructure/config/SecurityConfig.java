package com.example.jpaprac.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
                    .antMatchers("/auths/signUp","/auths/login","/ws/**", "/h2-console/**").permitAll()
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

    /**
     * 비밀번호 암호화용 PasswordEncoder Bean 등록
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}

package com.example.jpaprac.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //CORS관련 설정 추가 (클라이언트가 다른 도메인에서 요청을 보낼 수 있도록 허용하는 설정)
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //모든 경로에 대해 CORS설정을 적용
                .allowedOrigins("http://localhost:3000") //http://localhost:3000 에서 오는 요청만 허용
                .allowedMethods("*") //모든 HTTP메서드 (GET , POST, PUT, DELETE 등)을 허용
                .allowCredentials(true);
    }
}

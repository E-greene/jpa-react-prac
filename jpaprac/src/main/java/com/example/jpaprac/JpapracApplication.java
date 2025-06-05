package com.example.jpaprac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //@CreatedDate, @LastModifiedDate 쓰려면 해야함
@SpringBootApplication
public class JpapracApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpapracApplication.class, args);
	}

}

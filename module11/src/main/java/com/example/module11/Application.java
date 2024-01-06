package com.example.module11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import com.example.module11.service.UserService;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }
    
}

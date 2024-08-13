package com.example.rqchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableRetry
@EnableCaching
public class RqChallengeApplication {
    public static void main(String[] args) {
        SpringApplication.run(RqChallengeApplication.class, args);
    }
}
package com.hojun.learning_webserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@SpringBootApplication
@RestController
@Slf4j
public class LearningWebserverApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearningWebserverApplication.class, args);
    }

    @GetMapping("/")
    public String helloWorld() {
        return "Hello World";
    }
}

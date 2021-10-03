package com.hojun.learning_webserver.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsyncService {
    @Async("asyncTaskExecutor")
    public void asyncMethod(String message) {
        log.info(message);
    }
}

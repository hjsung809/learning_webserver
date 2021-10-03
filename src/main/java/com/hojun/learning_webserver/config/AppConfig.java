package com.hojun.learning_webserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@EnableScheduling
public class AppConfig {
    @Bean
    public ThreadPoolTaskExecutor asyncTaskExecutor() {
        var taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(50);
        taskExecutor.setQueueCapacity(50);

        return taskExecutor;
    }
}

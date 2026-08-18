package com.aitserver.global.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class AsyncConfig {

    @Bean(name = "notificationExecutor")
    public Executor notificationExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 1. 기본적으로 대기하고 있는 스레드 수
        executor.setCorePoolSize(10);
        // 2. Core가 꽉 차면 큐(Queue)에 요청을 쌓음. 큐의 최대 크기
        executor.setQueueCapacity(100);
        // 3. 큐마저 꽉 차면 최대 몇 개까지 스레드를 임시로 늘릴 것인가
        executor.setMaxPoolSize(30);
        // 4. 스레드 이름 접두사 (디버깅용)
        executor.setThreadNamePrefix("Noti-Async-");

        executor.initialize();
        return executor;
    }
}
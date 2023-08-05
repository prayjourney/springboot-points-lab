package com.zgy.learn.schedulesimple.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 方式3, 使用自定义的线程池, 代替默认的线程池
 */
@Configuration
public class SchedulePoolConfig {
    // 最大线程数量
    private static final int MAX_POOL_SIZE = 10;
    // 核心线程数量
    private static final int CORE_POOL_SIZE = 5;

    @Bean("my-Async-Schedule-TaskExecutor")
    public Executor taskExecutor() {
        // ThreadPoolTaskExecutor是Executor的实现类, ThreadPoolExecutor也是Executor的实现类, 后者是juc里面的实现
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        taskExecutor.setThreadNamePrefix("async-task-thread-pool");
        taskExecutor.initialize();
        return taskExecutor;
    }

}

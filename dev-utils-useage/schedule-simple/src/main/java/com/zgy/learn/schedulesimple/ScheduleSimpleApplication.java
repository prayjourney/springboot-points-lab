package com.zgy.learn.schedulesimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 开启定时任务
 */
@EnableAsync // 使用异步方式执行定时任务
@EnableScheduling
@SpringBootApplication
public class ScheduleSimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleSimpleApplication.class, args);
    }

}

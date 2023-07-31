package com.zgy.learn.dockerlogs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class TaskService {
    private String timeFormatter = "yyyy-MM-dd HH:mm:ss";

    @Scheduled(cron = "0 * * * * ?") //每一分钟都打印一次时间
    public String getTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String nowTime = localDateTime.format(DateTimeFormatter.ofPattern(timeFormatter)).toString();
        log.warn("又过了一分钟, 当前时间: {}", nowTime);
        return nowTime;
    }

}

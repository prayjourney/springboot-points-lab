package com.zgy.learn.bootswaggermailquartzmongoredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@SpringBootApplication()
@EnableScheduling // 开启定时任务
public class BootSwaggerMailQuartzMongoRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootSwaggerMailQuartzMongoRedisApplication.class, args);
    }

}

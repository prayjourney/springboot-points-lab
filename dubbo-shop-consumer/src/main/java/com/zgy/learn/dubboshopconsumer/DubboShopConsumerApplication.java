package com.zgy.learn.dubboshopconsumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class DubboShopConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboShopConsumerApplication.class, args);
    }

}

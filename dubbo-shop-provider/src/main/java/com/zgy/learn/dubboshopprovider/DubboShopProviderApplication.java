package com.zgy.learn.dubboshopprovider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class DubboShopProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboShopProviderApplication.class, args);
    }

}

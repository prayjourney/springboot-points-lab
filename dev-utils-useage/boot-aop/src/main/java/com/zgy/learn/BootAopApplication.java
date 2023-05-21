package com.zgy.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// @EnableAspectJAutoProxy //默认开启, 可以不用开启了
@SpringBootApplication
public class BootAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootAopApplication.class, args);
    }

}

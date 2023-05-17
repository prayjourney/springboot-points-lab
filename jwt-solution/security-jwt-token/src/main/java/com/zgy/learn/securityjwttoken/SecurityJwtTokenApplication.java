package com.zgy.learn.securityjwttoken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用spring security + jjwt + mybatis plus完成前后分离的认证
 */
@SpringBootApplication
public class SecurityJwtTokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityJwtTokenApplication.class, args);
    }

}

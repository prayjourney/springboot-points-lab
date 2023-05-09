package com.zgy.learn.verification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @ImportResource(locations = {"classpath:kaptcha.xml"}) // 导入配置文件, 则注释掉KaptchaConfig即可
public class VerificationKaptchaApplication {

    public static void main(String[] args) {
        SpringApplication.run(VerificationKaptchaApplication.class, args);
    }

}

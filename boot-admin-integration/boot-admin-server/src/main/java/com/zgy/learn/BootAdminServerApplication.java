package com.zgy.learn;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: pray-journey.io
 * @date 2022/4/9
 */
@EnableAdminServer // 开启Spring-Boot-Admin的服务端
@SpringBootApplication
public class BootAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootAdminServerApplication.class, args);

    }

}

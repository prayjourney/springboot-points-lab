package com.zgy.learn.dockerlogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DockerLogsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DockerLogsApplication.class, args);
    }

}

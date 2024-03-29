package com.zgy.multipledatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@SpringBootApplication
public class MultipleDatasourceByDynamicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDatasourceByDynamicApplication.class, args);
    }

}

package com.zgy.multipledatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis的分包使用多数据源的策略
 */
@SpringBootApplication
public class MultipleDatasourceByPackageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDatasourceByPackageApplication.class, args);
    }

}

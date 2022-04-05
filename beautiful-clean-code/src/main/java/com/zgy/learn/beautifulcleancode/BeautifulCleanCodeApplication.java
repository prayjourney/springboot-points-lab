package com.zgy.learn.beautifulcleancode;

import com.google.common.base.Preconditions;
import com.zgy.learn.beautifulcleancode.pojo.People;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeautifulCleanCodeApplication {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(BeautifulCleanCodeApplication.class, args);

        People people1 = new People(1, "张三", 50000);
        People people2 = new People(null, "法外狂徒", 500000);
        Preconditions.checkArgument(people1.getSalary() > 60000, "你不是50w!");
        check(people2);

    }

    // 抛出异常, 使用@SneakyThrows, 不去显式处理异常
    public static void check(People people) throws Exception {
        if (people.getSalary() < 500000) {
            throw new Exception("行走的50w");
        }
    }

}

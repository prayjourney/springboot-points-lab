package com.zgy.learn.beautifulcleancode;

import com.google.common.base.Preconditions;
import com.zgy.learn.beautifulcleancode.pojo.People;
import com.zgy.learn.beautifulcleancode.pojo.User;
import lombok.SneakyThrows;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2022/4/8
 * @modified:
 */
public class LombokAnnotationTest {

    @SneakyThrows
    public static void main(String[] args) {
        User.UserBuilder builder = User.builder();
        User buildUser = builder.id(1).address("深圳").email("helloworld@kkk.com").salary(50000).build();
        User buildUser02 = User.builder().id(1).address("深圳").email("helloworld@kkk.com").salary(50000).build();
        System.out.println(buildUser);
        System.out.println(buildUser02.toString());

        // 空构造器
        People people0 = new People();
        System.out.println(people0);
        People people1 = new People(1, "张三", 50000);
        People people2 = new People(null, "法外狂徒", 500000);
        People people3 = new People().setId(1).setName("法外狂徒李四").setTelephone("110");
        Preconditions.checkArgument(people1.getSalary() > 60000, "你不是50w!");
        check(people2);
        check(people3);
    }

    // 抛出异常, 使用@SneakyThrows, 不去显式处理异常
    public static void check(People people) throws Exception {
        if (people.getSalary() < 500000) {
            throw new Exception("行走的50w");
        }
    }

}

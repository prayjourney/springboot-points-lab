package com.zgy.learn.bootgson;

import com.zgy.learn.bootgson.pojo.PetHost;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * 使用java8的Optional 和Guava的Optional
 */
@SpringBootTest
class Java8OptionalTests {

    /************************************************java8的Optional***************************************************/
    /**
     * Optional.of(xxx), 创建Optional对象, 为null发生NPE(NullPointerException)
     */
    @Test
    public void testJava8Optional01() {
        PetHost petHost = new PetHost();
        Optional<String> petHostId = Optional.of(petHost.getId());
        System.out.println(petHost);
        System.out.println(petHostId.get());
    }

    /**
     * Optional.ofNullable(xxx), 创建Optional对象, xxx可以为null, 但是也获取不到什么值
     */
    @Test
    public void testJava8Optional02() {
        PetHost petHost = new PetHost();
        Optional<String> address = Optional.ofNullable(petHost.getAddress());
        // 如果存在就输出, 非null
        address.ifPresent(x -> {
            System.out.println(x);
        });
        System.out.println(address.get());
    }

    /**
     * Optional.empty(), 获取空的内容为null的Optional对象
     */
    @Test
    public void testJava8Optional03() {
        Optional<Object> empty = Optional.empty();
        empty.ifPresent(x -> {
            System.out.println("不为空!");
        });
        System.out.println("test...");
    }

    /**
     * orElse, 如果这个Optional对象为null, 就获取给定的默认值
     */
    @Test
    public void testJava8Optional04() {
        PetHost petHost = new PetHost();
        Optional<String> address = Optional.ofNullable(petHost.getAddress());
        String s = address.orElse("123");
        System.out.println(s);
    }

}

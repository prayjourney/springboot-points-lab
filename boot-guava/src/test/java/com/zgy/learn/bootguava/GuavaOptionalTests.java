package com.zgy.learn.bootguava;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2022/3/30
 * @modified:
 */

import com.google.common.base.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 使用java8的Optional 和Guava的Optional
 */
@SpringBootTest
class GuavaOptionalTests {

    /************************************************guava的Optional***************************************************/
    /**
     * Optional.of(xxx), 创建Optional对象, 为null发生NPE(NullPointerException)
     */
    @Test
    public void testGuavaOptional01() {
        // 一个空对象，这个不是空的，只是里面的内容为null
        PetHost petHost = new PetHost();
        Optional<PetHost> of = Optional.of(petHost);
        System.out.println(of);
    }

    @Test
    public void testGuavaOptional02() {
        // 一个空对象，这个不是空的，只是里面的内容为null
        PetHost petHost = new PetHost();
        Optional<String> of = Optional.of(petHost.getAddress());
        System.out.println(of);
    }

    /**
     * Optional.fromNullable(xxx), 创建Optional对象, xxx可以为null, 但是也获取不到什么值, isPresent测试是否存在
     */
    @Test
    public void testGuavaOptional03() {
        PetHost petHost = new PetHost();
        Optional<String> address = Optional.fromNullable(petHost.getAddress());
        // 如果存在就输出, 非null
        boolean present = address.isPresent();
        if (present) {
            System.out.println(address.get());
        } else {
            System.out.println("内容为null, 不存在!");
        }
    }

    /**
     * Optional.absent(), 获取空的内容为null的Optional对象
     */
    @Test
    public void testGuavaOptional05() {
        Optional<Object> empty = Optional.absent();
        if (empty.isPresent()) {
            System.out.println("不为空!");
        } else {
            System.out.println("为空!");
        }
    }

    /**
     * or, 如果这个Optional对象为null, 就获取给定的默认值, 但是默认值不能为null
     */
    @Test
    public void testGuavaOptional06() {
        PetHost petHost = new PetHost();
        Optional<String> address = Optional.fromNullable(petHost.getAddress());
        String dddd = address.or("dddd");
        System.out.println(dddd);
        // get获取值
        System.out.println(address.get());
    }

    /**
     * orNull, 如果这个Optional对象为null, 就获取给定的默认值, 这个默认值就是null
     */
    @Test
    public void testGuavaOptional07() {
        PetHost petHost = new PetHost();
        Optional<String> address = Optional.fromNullable(petHost.getAddress());
        String dddd = address.orNull();
        System.out.println(dddd);
    }

}


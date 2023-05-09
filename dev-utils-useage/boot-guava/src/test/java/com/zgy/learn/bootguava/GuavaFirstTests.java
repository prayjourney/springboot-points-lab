package com.zgy.learn.bootguava;

import com.google.common.base.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GuavaFirstTests {

    @Test
    public void testNUll01() {
        Optional<Integer> optional = Optional.of(5);
        System.out.println(optional.isPresent());
        System.out.println(optional.get());
    }

    @Test
    public void testNUll02() {
        Optional<Object> optional = Optional.of(null);
        System.out.println(optional.isPresent());
    }

    @Test
    public void testNUll03() {
        Object o = new Object();
        System.out.println(o == null);
        Optional<Object> optional = Optional.of(o);
        System.out.println(optional.isPresent());
    }

    @Test
    public void testNUll04() {
        PetHost testUser = new PetHost();
        System.out.println(testUser.toString());
        Optional<Object> optional1 = Optional.of(testUser);
        Optional<Object> optional2 = Optional.of(testUser.getId());
        System.out.println(optional1.isPresent());
        System.out.println(optional2.isPresent());
    }

    @Test
    public void testNUll05() {
        Object o = new Object();
        System.out.println(o);
        Optional<Object> optional = Optional.absent();
        System.out.println(optional.isPresent());
    }

    @Test
    public void testNUll06() {
        PetHost testUser = new PetHost();
        String or = Optional.of(testUser.getId()).or("1");
        System.out.println(or);
    }

    @Test
    public void testNUll07() {
        Object o = new Object();
        System.out.println(o == null);
        System.out.println(o);
        Optional<Object> optional = Optional.of(o);
        Object o1 = optional.orNull();
        System.out.println(o1 == null);
    }

}

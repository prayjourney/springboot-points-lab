package com.zgy.learn.bootguava;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2022/3/30
 * @modified:
 */

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Guava的Preconditions前置条件的判断, 参数检查
 */
@SpringBootTest
public class GuavaPreconditionsTests {

    @Test
    public void testNotNull01() {
        Preconditions.checkNotNull(null);
    }

    /**
     * 检测是否为空
     */
    @Test
    public void testNotNull02() {
        PetHost petHost = new PetHost();
        Preconditions.checkNotNull(petHost);
    }

    /**
     * 第一个是一个布尔表达式, 后面是错误消息模版
     */
    @Test
    public void testState01() {
        Integer a = 10;
        Integer b = 100;
        Preconditions.checkState(a - b > 0, "错误！");
    }

    /**
     * checkState的重载, 如果前面为false, 把后面的参数都打印出来, 下面的为例子
     * java.lang.IllegalStateException: error [100, 10]
     */
    @Test
    public void testState02() {
        Preconditions.checkState(false, "error", 100l, 10);
    }

    /**
     * 参数检测
     */
    @Test
    public void testCheckArg01() {
        Preconditions.checkArgument(true);
    }

    @Test
    public void testCheckArg02() {
        Integer a = 10;
        Integer b = 100;
        Preconditions.checkArgument(a - b > 0, "计算错误!");
    }

    /**
     * 在实际情况之中比如 id不能小于0, 这个时候就可以使用这种方式检查参数
     * java.lang.IllegalArgumentException: id的值0不能比小于10 [0, 10]
     */
    @Test
    public void testCheckArg03() {
        Integer id = 0;
        Integer minIdBound = 10;
        Preconditions.checkArgument(id > minIdBound, "id的值" + id + "不能比小于" + minIdBound, id, minIdBound);
    }

    /**
     * Objects.equal 和java.util.Objects.equals作用一样
     */
    @Test
    public void testObjects01() {
        boolean equal = Objects.equal(new PetHost(), new PetHost());
        System.out.println(equal);
    }

    @Test
    public void testObjects02() {
        boolean equal = Objects.equal(127, new Integer(127));
        System.out.println(equal);
    }

    @Test
    public void testObjects03() {
        boolean equal = Objects.equal(128, new Integer(128));
        System.out.println(equal);
    }

    @Test
    public void testObjects04() {
        boolean equal = Objects.equal("128", new Integer(128));
        System.out.println(equal);
    }

    @Test
    public void testObjects05() {
        boolean equal = Objects.equal("128", "128");
        System.out.println(equal);
    }

    @Test
    public void testObjects06() {
        boolean equal01 = Objects.equal("128", new String("128"));
        boolean equal02 = java.util.Objects.equals("128", new String("128"));
        System.out.println(equal01);
        System.out.println(equal02);
    }

}

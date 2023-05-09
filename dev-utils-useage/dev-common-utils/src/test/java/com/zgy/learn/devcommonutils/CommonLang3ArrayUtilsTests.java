package com.zgy.learn.devcommonutils;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommonLang3ArrayUtilsTests {

    @Test
    public void testArrayEmptyNull() {
        Integer[] ints01 = {};
        Integer[] ints02 = {1, 2, 3};
        String[] strings1 = new String[]{"1", "2", "3"};
        String[] strings2 = new String[3];
        System.out.println(ints01.length);
        System.out.println(ArrayUtils.isEmpty(ints01)); // true
        System.out.println(ArrayUtils.isEmpty(ints02)); // false
        System.out.println(ArrayUtils.isEmpty(strings1)); // false
        System.out.println(ArrayUtils.isEmpty(strings2)); // false
    }


    @Test
    public void testArrayCreate() {
        Integer[] ints01 = {};
        Integer[] ints02 = {1, 2, 3};
        String[] strings1 = new String[]{"1", "2", "3"};
        String[] strings2 = new String[3];
        // 添加对象到数组之中
        System.out.println(ArrayUtils.toString(ArrayUtils.add(ints02, 10)));
        System.out.println(ArrayUtils.toString(ArrayUtils.addAll(ints02, 10, 20, 30, 5)));
        // 检测是否包含某一个元素
        System.out.println(ArrayUtils.contains(ints02, 3));
        // 删除一个元素
        try {
            ArrayUtils.remove(strings1, 10);
        } catch (Exception e) {
            if (e instanceof IndexOutOfBoundsException) {
                System.out.println("IndexOutOfBoundsException");
            } else {
                System.out.println("发生错误!");
            }
        }
        System.out.println(ArrayUtils.toString(strings1));
        // 反转方向
        ArrayUtils.reverse(strings1);
        System.out.println(ArrayUtils.toString(strings1));

    }


}

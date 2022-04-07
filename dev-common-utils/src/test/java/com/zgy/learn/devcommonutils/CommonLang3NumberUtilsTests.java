package com.zgy.learn.devcommonutils;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommonLang3NumberUtilsTests {
    /**
     * isCreatable
     */
    @Test
    public void testStrIsNumber() {
        String helloStr = "helloworld!"; // 非数字
        String numStr1 = "10!"; // 不是数字
        String numStr2 = "10"; // 正整数
        String numStr3 = "+10"; // 带+号
        String numStr4 = "-10"; // 带-号
        String numStr5 = "10.88"; // 浮点数
        String numStr6 = "-10.88"; // 负浮点数
        String numStr7 = "10a"; // 16进制
        System.out.println(helloStr + "是数字:" + NumberUtils.isCreatable(helloStr)); // false
        System.out.println(numStr1 + "是数字:" + NumberUtils.isCreatable(numStr1)); // false
        System.out.println(numStr2 + "是数字:" + NumberUtils.isCreatable(numStr2)); // true
        System.out.println(numStr3 + "是数字:" + NumberUtils.isCreatable(numStr3)); // true
        System.out.println(numStr4 + "是数字:" + NumberUtils.isCreatable(numStr4)); // true
        System.out.println(numStr5 + "是数字:" + NumberUtils.isCreatable(numStr5)); // true
        System.out.println(numStr6 + "是数字:" + NumberUtils.isCreatable(numStr6)); // true
        System.out.println(numStr7 + "是数字:" + NumberUtils.isCreatable(numStr7)); // false
    }

    /**
     * createNumber
     */
    @Test
    public void testStr2Number() {
        String helloStr = "helloworld!"; // 非数字
        String numStr1 = "10!"; // 不是数字
        String numStr2 = "10"; // 正整数
        String numStr3 = "+10"; // 带+号
        String numStr4 = "-10"; // 带-号
        String numStr5 = "10.88"; // 浮点数
        String numStr6 = "-10.88"; // 负浮点数
        String numStr7 = "10a"; // 16进制
        // System.out.println(helloStr + "转换为:" + NumberUtils.createNumber(helloStr)); // java.lang.NumberFormatException
        // System.out.println(numStr1 + "转换为:" + NumberUtils.createNumber(numStr1)); // java.lang.NumberFormatExceptione
        System.out.println(numStr2 + "转换为:" + NumberUtils.createNumber(numStr2)); // true
        System.out.println(numStr3 + "转换为:" + NumberUtils.createNumber(numStr3)); // true
        System.out.println(numStr4 + "转换为:" + NumberUtils.createNumber(numStr4)); // true
        System.out.println(numStr5 + "转换为:" + NumberUtils.createNumber(numStr5)); // true
        System.out.println(numStr6 + "转换为:" + NumberUtils.createNumber(numStr6)); // true
        // System.out.println(numStr7 + "转换为:" + NumberUtils.createNumber(numStr7)); // java.lang.NumberFormatExceptione
    }

    /**
     * createNumber and type
     */
    @Test
    public void testStr2NumberType() {
        String numStr2 = "10"; // 正整数
        String numStr3 = "+10"; // 带+号
        String numStr4 = "-10"; // 带-号
        String numStr5 = "10.88"; // 浮点数
        String numStr6 = "10.88f"; // 浮点数f
        String numStr7 = "10L"; // 长整形
        String numStr8 = "-10.88"; // 负浮点数
        System.out.println(numStr2 + "转换为:" + NumberUtils.createNumber(numStr2) +
                "类型: " + ClassUtils.getCanonicalName(NumberUtils.createNumber(numStr2)));
        System.out.println(numStr3 + "转换为:" + NumberUtils.createNumber(numStr3));
        System.out.println(numStr4 + "转换为:" + NumberUtils.createNumber(numStr4));
        System.out.println(numStr5 + "转换为:" + NumberUtils.createNumber(numStr5) +
                "类型: " + ClassUtils.getCanonicalName(NumberUtils.createNumber(numStr5)));
        System.out.println(numStr6 + "转换为:" + NumberUtils.createNumber(numStr6));
        System.out.println(numStr7 + "转换为:" + NumberUtils.createNumber(numStr7)+
                "类型: " + ClassUtils.getCanonicalName(NumberUtils.createNumber(numStr7)));
        System.out.println(numStr8 + "转换为:" + NumberUtils.createNumber(numStr8));
    }
}

package com.zgy.learn.devcommonutils;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommonLang3StringUtilsTests {

    @Test
    public void testStrEmptyNull() {
        String nullStr = null;
        String emptyStr01 = "";
        String emptyStr02 = " ";
        String helloStr = "helloworld!";
        System.out.println(StringUtils.isEmpty(nullStr));
        System.out.println(StringUtils.isEmpty(emptyStr01));
        System.out.println(StringUtils.isEmpty(emptyStr02)); // 对于" ", 这种认为是不为空
        System.out.println(StringUtils.isEmpty(helloStr));
        System.out.println("===========================");
        System.out.println(StringUtils.isBlank(nullStr));
        System.out.println(StringUtils.isBlank(emptyStr01));
        System.out.println(StringUtils.isBlank(emptyStr02)); // 对于" ", 这种认为是为空的
        System.out.println(StringUtils.isBlank(helloStr));
    }

    @Test
    public void testStrReverse() {
        String helloStr = "helloworld!";
        System.out.println(StringUtils.reverse(helloStr)); // 字符串反向
        // 按照分隔符反向, 没有就不去起作用
        System.out.println(StringUtils.reverseDelimited("a.b.c", 'x')); // "a.b.c"
        System.out.println(StringUtils.reverseDelimited("a.b.c", '.')); // "c.b.a"
    }


    @Test
    public void testStrJoin() {
        String helloStr = "helloworld!";
        String name = "法外狂徒张三";
        String address = "shengzheng!";
        // 前面是一个数组或者list, 后面是分隔符, 分隔符任意
        System.out.println(StringUtils.join(new String[]{helloStr, name, address}, "="));
    }

    /**
     * 只认不带符号的正整数
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
        System.out.println(helloStr + "是数字:" + StringUtils.isNumeric(helloStr));
        System.out.println(numStr1 + "是数字:" + StringUtils.isNumeric(numStr1));
        System.out.println(numStr2 + "是数字:" + StringUtils.isNumeric(numStr2));
        System.out.println(numStr3 + "是数字:" + StringUtils.isNumeric(numStr3));
        System.out.println(numStr4 + "是数字:" + StringUtils.isNumeric(numStr4));
        System.out.println(numStr5 + "是数字:" + StringUtils.isNumeric(numStr5));
        System.out.println(numStr6 + "是数字:" + StringUtils.isNumeric(numStr6));
        System.out.println(numStr7 + "是数字:" + StringUtils.isNumeric(numStr7));
    }

    @Test
    public void testStrUpLow() {
        String helloStr = "helloWorlD!";
        String name = "法外狂徒张三";
        String address = "HAIDILao!";
        // 转换大小写
        System.out.println(StringUtils.lowerCase(helloStr));
        System.out.println(StringUtils.lowerCase(address));
        System.out.println(StringUtils.lowerCase(name));
        System.out.println(StringUtils.upperCase(helloStr));
        System.out.println(StringUtils.upperCase(address));
    }

    /**
     * abbreviate: 缩略, 后面加上三个...最小数字为4, null不返回任何
     * substring: 截取子字符串, 可以限定起始位置, 也可以只是有start
     * strip: 去掉指定的字符串, 默认是去掉字符串两侧的空白字符, 也可以指定
     * replace: 把字符串之中的部分字符串替换, 可以指定替换的次数, 从左到右计数
     * left/right: 从左边/右边取字符串里面的信息, 超过长度了取不到
     * leftPad/rightPad:  从左边/右边取字符串里面的信息, 超过长度了默认用空白填充, 可以指定填充的字符或者字符串
     */
    @Test
    public void testStrCreateStr() {
        String helloStr = "helloWorlDhoo!";
        String name = "法外狂徒张三";
        String address = "HAIDILao!";
        String info = " 我是一只小小鸟 ! 可以 可是  ";
        System.out.println(StringUtils.abbreviate(helloStr, 8));
        System.out.println(StringUtils.substring(helloStr, 0, 10));
        System.out.println(StringUtils.strip(info));
        System.out.println(StringUtils.repeat(name, 2));
        System.out.println(StringUtils.replace(helloStr, "o", "代替", 3));
        System.out.println(StringUtils.left("abcdefg", 4)); //"abcd"
        System.out.println(StringUtils.leftPad("bat", 5, '*'));  //"  bat"
    }

}

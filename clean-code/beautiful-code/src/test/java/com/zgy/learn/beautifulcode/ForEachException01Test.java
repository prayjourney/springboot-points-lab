package com.zgy.learn.beautifulcode;

import java.util.Arrays;
import java.util.List;

/**
 * 一个循环之中出现Exception
 * <p>
 * 1. 抛出异常不处理: 所有它后续的操作, 都会停止
 * 2. 抛出异常处理了: 所有它后续的操作, 如果能正常运行, 就会正常运行
 */
public class ForEachException01Test {

    public static void main(String[] args) throws Exception {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //System.out.println(forEachTest01(list));
        System.out.println(forEachTest02(list));
        System.out.println("hello world!");
    }

    /**
     * 直接抛出异常, 后面没法继续做
     */
    public static String myTest01(Integer num) throws Exception {
        String result = "";
        if (num == 5) {
            throw new Exception("发生错误...");
            // 抛出异常后, 它后面不能跟语句了, 相当于是这一层结果已经return了, 后面跟上的都是Unreachable statement, 不可到达的语句
            // return "error..."; // Unreachable statement
        } else {
            result = result + num;
        }
        return result;
    }

    /**
     * 处理了异常, 后面就可以接着做
     */
    public static String myTest02(Integer num) {
        String result = "";
        try {
            if (num == 5) {
                throw new Exception("发生错误...");
                // return "error..."; // Unreachable statement
            } else {
                result = result + num;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 未处理Exception
     * <p>
     * * 结果
     * * Exception in thread "main" java.lang.Exception: 发生错误...
     * *     at com.zgy.learn.beautifulcleancode.ForEachException01Test.myTest01(ForEachException01Test.java:29)
     * *     at com.zgy.learn.beautifulcleancode.ForEachException01Test.forEachTest01(ForEachException01Test.java:55)
     * *     at com.zgy.learn.beautifulcleancode.ForEachException01Test.main(ForEachException01Test.java:16)
     */
    public static String forEachTest01(List<Integer> nums) throws Exception {
        StringBuilder result = new StringBuilder();
        for (Integer num : nums) {
            String myTest01 = myTest01(num);
            result.append(myTest01);
        }
        return result.toString();
    }

    /**
     * 处理了Exception
     * <p>
     * * 结果
     * * java.lang.Exception: 发生错误...
     * *     at com.zgy.learn.beautifulcleancode.ForEachException01Test.myTest02(ForEachException01Test.java:41)
     * *     at com.zgy.learn.beautifulcleancode.ForEachException01Test.forEachTest02(ForEachException01Test.java:64)
     * *     at com.zgy.learn.beautifulcleancode.ForEachException01Test.main(ForEachException01Test.java:17)
     * * 12346789
     * * hello world!
     */
    public static String forEachTest02(List<Integer> nums) {
        StringBuilder result = new StringBuilder();
        for (Integer num : nums) {
            String myTest02 = myTest02(num);
            result.append(myTest02);
        }
        return result.toString();
    }

}

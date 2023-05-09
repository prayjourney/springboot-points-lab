package com.zgy.learn.beautifulcode;

import java.util.Arrays;
import java.util.List;

/**
 * 一个循环之中出现Exception
 * <p>
 * 1. 抛出异常不处理: 所有它后续的操作, 都会停止
 * 2. 抛出异常处理了: 所有它后续的操作, 如果能正常运行, 就会正常运行
 */
public class ForEachException02Test {

    public static void main(String[] args) throws Exception {
        //System.out.println(myTest01(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
        System.out.println(myTest02(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
        System.out.println("hello world!");
    }

    /**
     * 直接抛出异常
     * <p>
     * * 结果
     * * Exception in thread "main" java.lang.Exception: 发生Exception...
     * *     at com.zgy.learn.beautifulcleancode.ForEachExceptionTest.myTest02(ForEachExceptionTest.java:47)
     * *     at com.zgy.learn.beautifulcleancode.ForEachExceptionTest.main(ForEachExceptionTest.java:13)
     * *
     */
    public static String myTest01(List<Integer> nums) throws Exception {
        String result = "";
        for (Integer num : nums) {
            if (num == 5) {
                throw new Exception("发生Exception...");
                // 抛出异常后, 它后面不能跟语句了, 相当于是这一层结果已经return了, 后面跟上的都是Unreachable statement, 不可到达的语句
                // return "error..."; // Unreachable statement
            } else {
                result = result + num;
                // return result; // 在这儿return, 直接返回, return就是强制结束
            }
        }
        return result;
    }

    /**
     * 处理抛出的Exception
     * <p>
     * * 结果
     * * java.lang.RuntimeException: 发生Runtime Exception...
     * *     at com.zgy.learn.beautifulcleancode.ForEachExceptionTest.myTest03(ForEachExceptionTest.java:60)
     * *     at com.zgy.learn.beautifulcleancode.ForEachExceptionTest.main(ForEachExceptionTest.java:13)
     * * 12346789
     * * hello world!
     */
    public static String myTest02(List<Integer> nums) throws Exception {
        String result = "";
        for (Integer num : nums) {
            try {
                if (num == 5) {
                    throw new RuntimeException("发生Runtime Exception...");
                } else {
                    result = result + num;
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}

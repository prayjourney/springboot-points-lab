package com.zgy.learn.beautifulcode;

/**
 * 异常是否导致程序停止
 */
public class ExceptionShutdownTest {
    public static void main(String[] args) throws Exception {
        testException("");
        System.out.println("hello world!");
    }


    public static String testException(String str) throws Exception {
        // 抛出异常
        if (str.length() == 0) {
            throw new Exception("error");
        }
        return "error!";
    }

}

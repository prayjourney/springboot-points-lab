package com.zgy.learn.beautifulcode;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class CheckedExceptionTest {
    public static void main(String[] args) throws Exception {
        String str1 = "d://临江仙·柳絮.txt";
        String str2 = "d://hello.txt";
        System.out.println(readFile01(str1));
        System.out.println(readFile01(str2));

        System.out.println(readFile02(""));

    }

    public static String readFile01(String str) {
        // try-catch-finally处理异常
        try {
            File file = new File(str);
            return FileUtils.readFileToString(file);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally一定会执行!");
        }
        return "error!";
    }

    public static String readFile02(String str) throws Exception {
        // 抛出异常
        if (str.length() == 0) {
            throw new Exception("error");
        }
        return "error!";
    }

}

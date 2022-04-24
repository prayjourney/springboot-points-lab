package com.zgy.learn.beautifulcode;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ExceptionAndReturnTest {
    public static void main(String[] args) throws IOException {
        // System.out.println(readFile01(""));
        // System.out.println(readFile02(""));
        System.out.println(readFile03(""));
    }

    public static String readFile01(String str) {
        // try-catch-finally处理异常
        try {
            File file = new File(str);
            String content = FileUtils.readFileToString(file);
            return content;
        } catch (Exception e) {
            System.out.println("readFile01发生错误!");
            e.printStackTrace();
            return "return: readFile01: catch之中的返回值!";     // A
        } finally {
            System.out.println("finally一定会执行!readFile01");
            return "return: readFile01 finally~~~";           // B
        }
        // return "return: readFile01!";                      // C
    }

    public static String readFile02(String str) {
        // try-catch处理异常
        try {
            File file = new File(str);
            String content = FileUtils.readFileToString(file);
            return content;
            // return FileUtils.readFileToString(file);
        } catch (Exception e) {
            System.out.println("readFile02发生错误!");
            e.printStackTrace();
            // return "return: readFile02: catch之中的返回值!";
        }
        return "return: readFile02!";
    }

    public static String readFile03(String str) throws IOException {
        // try-finally处理异常
        try {
            File file = new File(str);
            String content = FileUtils.readFileToString(file);
            return content;
        } finally {
            System.out.println("finally一定会执行!readFile03");
            return "return: readFile03: finally之中的返回值!";
        }
        // Unreachable statement, 下面在无法添加返回String了
        // return content;
    }
}

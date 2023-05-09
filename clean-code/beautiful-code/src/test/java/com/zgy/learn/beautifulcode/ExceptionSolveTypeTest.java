package com.zgy.learn.beautifulcode;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ExceptionSolveTypeTest {
    public static void main(String[] args){
        /**
         * 1.try-catch-finally处理异常
         * 2.try-catch处理异常
         * 3.try-finally处理异常
         */
        // 第3种方式, 无法在最后添加return语句了, 有其他调用者的话, 需要处理或者抛出异常
        // solveException01("");
        // solveException02("");
        try {
            solveException03("");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String solveException01(String str) {
        // try-catch-finally处理异常
        String content = "";
        try {
            File file = new File(str);
            content = FileUtils.readFileToString(file);
            return content;
        } catch (Exception e) {
            System.out.println("solveException01发生错误!");
            e.printStackTrace();
        } finally {
            System.out.println("finally一定会执行!solveException01");
        }
        return content;
    }

    public static String solveException02(String str) {
        // try-catch处理异常
        String content = "";
        try {
            File file = new File(str);
            content = FileUtils.readFileToString(file);
            return content;
        } catch (Exception e) {
            System.out.println("solveException02发生错误!");
            e.printStackTrace();
        }
        return content;
    }

    public static String solveException03(String str) throws IOException {
        // try-finally处理异常
        String content = "";
        try {
            File file = new File(str);
            content = FileUtils.readFileToString(file);
            return content;
        } finally {
            System.out.println("finally一定会执行!solveException03");
            return "finally一定会执行!solveException03";
        }
        // Unreachable statement, 下面在无法添加返回String了
        // return content;
    }

}

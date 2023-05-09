package com.zgy.learn.beautifulcode;

public class RuntimeExceptionTest {
    public static void main(String[] args) {
        // ArrayIndexOutOfBoundsException
        Integer[] arrays = new Integer[]{1, 2, 3};
        System.out.println(arrays[4]);

        // ArithmeticException
        Integer res = 10 / 0;
        System.out.println(res);
    }

}

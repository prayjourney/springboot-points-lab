package com.zgy.learn.beautifulcode.biz.exception;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2022/3/30
 * @modified:
 */
public class SysException extends RuntimeException {
    private int code;

    public SysException(MessageCode messageCode) {
        super(messageCode.messageVal());
        this.code = messageCode.codeVal();
    }

    public SysException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

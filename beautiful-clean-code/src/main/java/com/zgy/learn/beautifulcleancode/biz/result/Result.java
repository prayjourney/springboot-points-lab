package com.zgy.learn.beautifulcleancode.biz.result;

import com.zgy.learn.beautifulcleancode.biz.exception.MessageCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2022/3/30
 * @modified:
 */
@Data
public class Result implements Serializable {
    private int code;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(MessageCode messageCode) {
        this.code = messageCode.codeVal();
        this.message = messageCode.messageVal();
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}


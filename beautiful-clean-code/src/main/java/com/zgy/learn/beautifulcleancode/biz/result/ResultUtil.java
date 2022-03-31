package com.zgy.learn.beautifulcleancode.biz.result;

import com.zgy.learn.beautifulcleancode.biz.exception.MessageCode;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2022/3/30
 * @modified:
 */
public class ResultUtil {
    public static Result success() {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("成功");
        result.setData(null);
        return result;
    }

    public static Result success(Object obj) {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("成功");
        result.setData(obj);
        return result;
    }

    public static Result error(MessageCode messageCode) {
        Result result = new Result();
        result.setCode(messageCode.codeVal());
        result.setMessage(messageCode.messageVal());
        result.setData(null);
        return result;
    }

    public static Result error(MessageCode messageCode, Object obj) {
        Result result = new Result();
        result.setCode(messageCode.codeVal());
        result.setMessage(messageCode.messageVal());
        result.setData(obj);
        return result;
    }

    public static Result error(int code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(null);
        return result;
    }

    public static Result error(int code, String message, Object obj) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(obj);
        return result;
    }

}

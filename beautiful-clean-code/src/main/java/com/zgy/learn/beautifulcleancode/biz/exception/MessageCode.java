package com.zgy.learn.beautifulcleancode.biz.exception;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2022/3/30
 * @modified:
 */
public enum MessageCode {
    // 服务异常
    SYS_ERROR(300, "系统异常"),
    BIZ_ERROR(301, "业务异常"),
    NET_ERROR(302, "网络不可用"),

    // 内部异常
    NULL_POINTER_ERROR(400, "空指针异常"),
    MATH_CACL_ERROR(401, "数学运算异常"),

    // 具体业务异常
    ARGUMENT_ERROR(500, "参数错误"),
    ID_ERROR(501, "ID不能小于0"),
    NAME_ERROR(502, "用户名错误"),
    AGE_ERROR(503, "用户年龄错误");


    private int code;
    private String message;

    MessageCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int codeVal() {
        return this.code;
    }

    public String messageVal() {
        return this.message;
    }

}

package com.zgy.learn.handle;

import lombok.Getter;

/**
 * @author: pray-journey.io
 * @date: created in 2022/11/5
 * @description:
 */
@Getter
public enum MessageCode {
    PARAM_ERROR(203, "参数错误"),
    NO_PALACE_FOUND(301, "未找到目的地"),
    NO_TOOLS_FOUND(302, "未找到交通工具"),
    NO_THINGS_FOUND(401, "未找到商品"),
    SERVICE_ERROR(501, "服务出错");
    private Integer code;
    private String message;

    MessageCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}

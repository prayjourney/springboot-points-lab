package com.zgy.learn.handle;

import lombok.Getter;

/**
 * @author: pray-journey.io
 * @date: created in 2022/11/5
 * @description:
 */
@Getter
public class BizException extends RuntimeException {
    private MessageCode messageCode;

    public BizException(MessageCode messageCode) {
        this.messageCode = messageCode;
    }

}

package com.zgy.learn.beautifulcode.handler;

import com.zgy.learn.beautifulcode.biz.exception.BizException;
import com.zgy.learn.beautifulcode.biz.exception.MessageCode;
import com.zgy.learn.beautifulcode.biz.exception.SysException;
import com.zgy.learn.beautifulcode.biz.result.Result;
import com.zgy.learn.beautifulcode.biz.result.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.time.format.DateTimeParseException;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2022/3/30
 * @modified:
 * @ref: https://blog.csdn.net/Lei_Da_Gou/article/details/80481846, https://www.cnblogs.com/xuwujing/p/10933082.html
 * https://notemi.cn/spring-boot-implementation---enhancer-to-implement-global-exception-handler.html
 */
@Slf4j
@ControllerAdvice
public class BizExceptionHandler {
    /**
     * 系统异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = SysException.class)
    @ResponseBody
    public Result exceptionHandler(SysException e) {
        log.error("系统异常...", e);
        return ResultUtil.error(MessageCode.SYS_ERROR);
    }

    /**
     * 业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Result exceptionHandler(BizException e) {
        log.error("业务异常...", e);
        return ResultUtil.error(MessageCode.BIZ_ERROR);
    }

    /**
     * 空指针的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Result exceptionHandler(NullPointerException e) {
        log.error("空指针异常...", e);
        return ResultUtil.error(MessageCode.NULL_POINTER_ERROR);
    }

    /**
     * 普通异常
     * 一次性捕捉多种异常，然后去按照类型处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e) {
        log.error("普通异常...", e);
        if (e instanceof NumberFormatException) {
            return ResultUtil.error(MessageCode.MATH_CACL_ERROR);
        } else if (e instanceof DateTimeParseException) {
            return ResultUtil.error(MessageCode.ARGUMENT_ERROR);
        } else if (e instanceof ConstraintViolationException) {
            return ResultUtil.error(MessageCode.ARGUMENT_CHECK_ERROR, e.getMessage());
        } else if (e instanceof BindException) {
            return ResultUtil.error(MessageCode.ARGUMENT_BIND_ERROR, e.getMessage());
        } else if (e instanceof BindException) {
            return ResultUtil.error(MessageCode.MESSAGE_CONVERT_ERROR, e.getMessage());
        } else {
            return ResultUtil.error(1000, "其他异常", e.getMessage());
        }
    }

}

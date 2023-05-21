package com.zgy.learn.aop;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: pray-journey.io
 * @date: created in 2022/11/5
 * @description:
 */
@Order(100)
@Slf4j
@Aspect
@Component
public class LogAspect {
    // 定义切点
    @Pointcut("execution(* com.zgy.learn.controller.*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void beforeLog(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("params: ");
        for (Object obj : args) {
            stringBuilder.append(obj + ",");
        }
        log.info("开始进入处理, 进入的方法是:{}, 方法参数:{}, 时间是:{}", joinPoint.getSignature().getDeclaringTypeName(),
                stringBuilder.substring(0, stringBuilder.length() - 1), DateUtil.now());
    }

    @After("pointCut()")
    public void afterLog(JoinPoint joinPoint) {
        log.info("处理完毕, 时间是:{}", DateUtil.now());
    }

}

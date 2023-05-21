package com.zgy.learn.securityjwttoken.aop;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 1. 定义切面
 * 2. 定义切点pointCut
 * 3. 定义通知advice
 */
@Slf4j
@Aspect // 定义成切面
@Component // 让Spring管理
public class LoginAspect {

    /**
     * 登录方法的pointCut
     */
    @Pointcut("execution(* com.zgy.learn.securityjwttoken.controller.LoginController.login(String, String))")
    public void loginPointCut() {
    }

    /**
     * 记录日志的pointCut
     */
    @Pointcut("execution(* com.zgy.learn.securityjwttoken.controller.*.*(..))")
    public void logPointCut() {
    }

    /**
     * 定义环绕通知Advice
     *
     * @param joinPoint
     */
    @Around("loginPointCut()")
    public Object aroundLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("params: ");
        for (Object obj : args) {
            stringBuilder.append(obj + ",");
        }

        Object result = null;
        try {
            // @Before
            log.info("方法{}开始处理, 参数:{}, 时间是:{}", joinPoint.getSignature().getDeclaringTypeName(),
                    stringBuilder.substring(0, stringBuilder.length() - 1), DateUtil.now());
            result = joinPoint.proceed(); // 就是利用反射调用目标方法即可, 就是method.invoke(obj, args)
            // @AfterReturning
            log.info("方法{}处理完毕, 处理结果:{}, 时间是:{}", joinPoint.getSignature().getDeclaringTypeName(),
                    result, DateUtil.now());
        } catch (Throwable e) {
            // @AfterThrowing
            log.error("方法{}处理出现异常, 时间是:{}, 异常信息:{}", joinPoint.getSignature().getDeclaringTypeName(),
                    DateUtil.now(), e.getMessage());
            throw new RuntimeException(e); // 为了让外界能知道这个异常，这个异常一定要抛出去
        }
        return result; // 反射调用后的返回值也一定返回出去，不返回会空指针
    }

    @After("logPointCut()")
    public void afterLog(JoinPoint point) {
        Signature signature = point.getSignature();
        String method = signature.getName();
        log.info("{}处理完毕, 时间是:{}", method, DateUtil.now());
    }

    @AfterThrowing(pointcut = "logPointCut()", throwing = "ex")
    public void exceptionAdvice(JoinPoint point, Throwable ex) {
        Signature signature = point.getSignature();
        String method = signature.getName();
        log.error("执行方法{}出错, 时间是:{}, 错误信息：{}", method, DateUtil.now(), ex.getMessage());

    }

}

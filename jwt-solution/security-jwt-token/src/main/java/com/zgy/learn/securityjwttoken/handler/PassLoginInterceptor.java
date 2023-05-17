package com.zgy.learn.securityjwttoken.handler;

import com.zgy.learn.securityjwttoken.annotation.PassLogin;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 跳过登录验证
 */
@Component
public class PassLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 检查是否有PassLogin注解, 有则跳过认证
        if (method.isAnnotationPresent(PassLogin.class)) {
            PassLogin passToken = method.getAnnotation(PassLogin.class);
            if (passToken.required()) {
                return true;
            }
            return false;
        }
        return true;
    }

}

package com.zgy.learn.securityjwttoken.handler;

import com.zgy.learn.securityjwttoken.annotation.NotLogin;
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
public class NotLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 检查是否有NotLogin注解, 有则不用登录就可以访问
        if (method.isAnnotationPresent(NotLogin.class)) {
            NotLogin notLogin = method.getAnnotation(NotLogin.class);
            if (notLogin.required()) {
                return true;
            }
            return false;
        }
        return true;
    }

}

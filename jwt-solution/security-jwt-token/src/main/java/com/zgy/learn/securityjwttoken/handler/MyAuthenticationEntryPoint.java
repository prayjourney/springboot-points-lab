package com.zgy.learn.securityjwttoken.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * token认证失败
 * AuthenticationEntryPoint 用来解决匿名用户访问无权限资源时的异常
 * AccessDeniedHandler      用来解决认证过的用户访问无权限资源时的异常
 */
@Slf4j
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        log.info("authentication failed...");
        // token认证失败
        HashMap map = new HashMap();
        map.put("code", 402);
        map.put("msg", "token is invalid");
        response.getWriter().println(map.toString());
    }

}

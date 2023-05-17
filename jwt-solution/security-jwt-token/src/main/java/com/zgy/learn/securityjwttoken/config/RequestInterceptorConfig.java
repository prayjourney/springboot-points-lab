package com.zgy.learn.securityjwttoken.config;

import com.zgy.learn.securityjwttoken.handler.NotLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: 自定义注解拦截配置
 */
@Configuration
public class RequestInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private NotLoginInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }

}

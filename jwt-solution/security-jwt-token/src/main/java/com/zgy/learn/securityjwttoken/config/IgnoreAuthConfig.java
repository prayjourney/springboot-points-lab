package com.zgy.learn.securityjwttoken.config;

import com.zgy.learn.securityjwttoken.annotation.NotLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * 需要忽略security检查的接口的配置
 */
@Slf4j
@Configuration
public class IgnoreAuthConfig implements ApplicationContextAware {
    @Resource
    private IgnoreAuthProperties ignoreAuthProperties;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        initProperties(ignoreAuthProperties);
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(RestController.class);
        beanMap.forEach((k, v) -> {
            // controller配置的访问路径
            String baseUrl = "";
            Class<?> controllerClass = v.getClass();
            // controller上如果存在RequestMapping注解, 则获取类的URL路径, 否则为""
            RequestMapping annotation = AnnotatedElementUtils.findMergedAnnotation(controllerClass, RequestMapping.class);
            if (annotation != null) {
                baseUrl = annotation.value().length > 0 ? annotation.value()[0] : "";
            }
            // 判断访问路径前缀是否包含/
            if (!baseUrl.startsWith("/")) {
                baseUrl = "/" + baseUrl;
            }

            // 获取当前controller之中所有声明的方法
            Method[] allMethods = controllerClass.getMethods();
            String methodType;
            GetMapping getMapping;
            PostMapping postMapping;
            NotLogin notLoginInfo;

            for (Method method : allMethods) {
                // 判断方法是否使用忽略权限认证注解
                notLoginInfo = AnnotatedElementUtils.findMergedAnnotation(method, NotLogin.class);
                if (notLoginInfo != null) {
                    String url = "";
                    methodType = "";
                    // 当注解没配置接口名称时候使用接口名称(Controller访问路径+接口访问路径)
                    // 目前只适配了PostMapping和GetMapping注解,其它类型请自行扩展
                    postMapping = AnnotatedElementUtils.findMergedAnnotation(method, PostMapping.class);
                    if (postMapping != null) {
                        url = postMapping.value().length > 0 ? postMapping.value()[0] : "";
                        methodType = "post";
                    } else {
                        getMapping = AnnotatedElementUtils.findMergedAnnotation(method, GetMapping.class);
                        if (getMapping != null) {
                            url = getMapping.value().length > 0 ? getMapping.value()[0] : "";
                            methodType = "get";
                        }
                    }
                    if (url.trim().length() > 0) {
                        url = (baseUrl + "/" + url).replaceAll("/+", "/");
                    } else {
                        url = baseUrl;
                    }
                    if ("post".equals(methodType)) {
                        ignoreAuthProperties.getPostUrl().add(url);
                    } else if ("get".equals(methodType)) {
                        ignoreAuthProperties.getGetUrl().add(url);
                    }
                }
            }
        });
        log.info("需要注解忽略的get请求类型接口路径如下");
        for (String getUrl : ignoreAuthProperties.getGetUrl()) {
            log.info(getUrl);
        }
        log.info("需要注解忽略的post请求类型接口路径如下");
        for (String postUrl : ignoreAuthProperties.getPostUrl()) {
            log.info(postUrl);
        }
    }

    private IgnoreAuthProperties initProperties(IgnoreAuthProperties properties) {
        if (Objects.isNull(properties.getAllUrl())) {
            properties.setAllUrl(new ArrayList<>());
        }
        if (Objects.isNull(properties.getGetUrl())) {
            properties.setGetUrl(new ArrayList<>());
        }
        if (Objects.isNull(properties.getPostUrl())) {
            properties.setPostUrl(new ArrayList<>());
        }
        return properties;
    }

}

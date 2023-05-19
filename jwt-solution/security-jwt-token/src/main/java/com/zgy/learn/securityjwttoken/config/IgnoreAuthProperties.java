package com.zgy.learn.securityjwttoken.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 配置忽略security权限校验的接口
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "security.ignore")
public class IgnoreAuthProperties {
    private List<String> allUrl;

    private List<String> getUrl;

    private List<String> postUrl;

}

package com.zgy.learn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// 属性绑定
@Data
@Configuration
@ConfigurationProperties(prefix = "prop")
public class PropProperties {
    private String hi;
    private String name;

}

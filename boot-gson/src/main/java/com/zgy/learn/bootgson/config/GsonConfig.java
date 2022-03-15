package com.zgy.learn.bootgson.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: pray-journey.io
 * @description: 创建Gson, 三种不同方式, Gson是用来进行转换使用的, GsonBuilder是去做一些基础配置
 * @date: created in 2022/3/16
 * @modified:
 */
@Configuration
public class GsonConfig {
    @Bean(name = "gson")
    public Gson gson() {
        return new Gson();
    }

    @Bean(name = "gsonbybuilder")
    public Gson gsonbybuilder() {
        return new GsonBuilder().create();
    }

    @Bean(name = "gsonsettype")
    public Gson gsonsettype() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

}

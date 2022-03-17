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
        return new GsonBuilder()
                .serializeNulls() // 默认情况下如果某一个属性为null, 那么此属性不会参与序列化和反序列化的过程, 加上此属性后会参与序列化和反序列化的过程
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }
    // setPrettyPrinting(): 格式化json字符串的输出, 默认情况下是输出一行, 经过这个属性设置后会格式化输出, 即有缩进的输出
    // {"p1":{"id":"18239fg1","name":"张璇","age":22,"gender":1,"address":"北京","birthday":"2022-03-17 11:59:19"}}
    /**
    {
        "p1": {
        "id": "18239fg1",
                "name": "张璇",
                "age": 22,
                "gender": 1,
                "address": "北京",
                "birthday": "2022-03-17 11:59:19"
        }
    }
    */

}

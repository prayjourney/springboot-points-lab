package com.zgy.learn.bootgson;

import com.google.gson.Gson;
import com.zgy.learn.bootgson.pojo.Hedgehog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * Gson对象就是用来转化使用的, Gson的toJson转成json字符串, fromJson转成Object
 */
@SpringBootTest
class OtherGsonTests {
    @Autowired
    @Qualifier(value = "gsonsettype")
    private Gson gson;

    @Test
    void contextLoads() {
    }

    // javaBean-->json
    @Test
    public void bean2JsonTest() {
        Hedgehog hedgehog = new Hedgehog();
        hedgehog.setAge(1).setId(1).setName("cici").setOriginAddress("贵州遵义").setCatchTime(new Date());
        String jsonStr = gson.toJson(hedgehog);
        System.out.println(jsonStr);
    }

    // json-->javaBean
    @Test
    public void json2BeanTest() {
        String jsonStr = "{\n" +
                "  \"id\": 1,\n" +
                "  \"age\": 1,\n" +
                "  \"name\": \"cici\",\n" +
                "  \"origin_address\": \"贵州遵义\",\n" +
                "  \"catch_time\": \"2022-03-17 14:16:20\"\n" +
                "}";
        Hedgehog hedgehog = gson.fromJson(jsonStr, Hedgehog.class);
        System.out.println(hedgehog.toString());
    }

}

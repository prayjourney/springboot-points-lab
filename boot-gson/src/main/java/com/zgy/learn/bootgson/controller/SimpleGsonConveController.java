package com.zgy.learn.bootgson.controller;

import com.google.gson.JsonObject;
import com.zgy.learn.bootgson.util.Converter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: pray-journey.io
 * @description: 简单的转换, 默认使用的是jackson, 所以直接返回Gson的对象或者数组，会报错
 * @date: created in 2022/3/16
 * @modified:
 */
@RestController
@RequestMapping("converter")
public class SimpleGsonConveController {

    @Resource
    private Converter converter;

    @RequestMapping("str2Obj")
    public String str2Obj() {
        // https://www.bejson.com/ json转义得到json字符串
        String sss = "{\"country\":\"ZHN\",\"city\":\"SZ\",\"summary\":{\"population\":\"19000000\",\"area\":\"18000km^2\"}}";
        JsonObject jsonObject = converter.str2Obj(sss);
        return jsonObject.toString();
    }

    @RequestMapping("str2Obj01")
    public String str2Obj01() {
        String s = "{\"country\":\"ZHN\",\"city\":\"SZ\",\"summary\":{\"population\":\"19000000\",\"area\":\"18000km^2\"}}";
        String sss = "{\"country\":\"ZHN\",\"summary\":{\"population\":\"19000000\",\"area\":\"8000km^2\", \"birthday\": \"Wed Mar 16 01:07:24 CST 2022\"}}";
        JsonObject jsonObject01 = converter.str2Obj(s);
        JsonObject jsonObject02 = converter.str2Obj(sss);
        return jsonObject02.toString();
    }

    /**
     * {\"country\":\"ZHN\",\"summary\":{\"population\":\"19000000\",\"area\":\"18000km^2\", \"birthday\": \"Wed Mar 16 01:07:24 CST 2022\"}
     *
     * @param jsonStr
     * @return
     */
    @RequestMapping("str2Obj02")
    public String str2Obj02(String jsonStr) {
        return converter.str2Obj(jsonStr).toString();
    }


    @RequestMapping("str2Array")
    public String str2Array() {
        String s = "[\"张三\",\"李四\",\"王二麻子\",{\"name\":\"韩梅梅\",\"age\":28}]";
        return converter.str2Array(s).toString();
    }

    /**
     * [\"张三\",\"李四\",\"王二麻子\",{\"name\":\"韩梅梅\",\"age\":28}]
     *
     * @return
     */
    @RequestMapping("str2Array01")
    public String str2Array01(String jsonStr) {
        return converter.str2Array(jsonStr).toString();
    }

}

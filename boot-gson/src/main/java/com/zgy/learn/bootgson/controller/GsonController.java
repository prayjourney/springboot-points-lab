package com.zgy.learn.bootgson.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2022/3/16
 * @modified:
 */
@RestController
@RequestMapping("gson")
public class GsonController {

    /**
     * 创建一个JsonObject
     * JsonObject里面只能存放字符串, 数字, 布尔类型, 嵌套JsonObject或者是JsonArray
     *
     * @return
     */
    @RequestMapping("create/jsonobject")
    public String createJsonObject() {
        JsonObject jsonObject = new JsonObject();
        // 添加属性
        jsonObject.addProperty("country", "ZHN");
        jsonObject.addProperty("city", "SZ");
        JsonObject object = new JsonObject();
        object.addProperty("population", "19000000");
        object.addProperty("area", "18000km^2");
        // 套了一层
        jsonObject.add("summary", object);
        System.out.println(object);
        System.out.println(jsonObject);

        // 打印JsonObject的字符串
        return jsonObject.toString();

    }

    /**
     * 创建一个JsonArray
     * 创建一个JsonArray里面只能存放字符串, 数字, 布尔类型, 嵌套JsonObject或者是JsonArray
     *
     * @return
     */
    @RequestMapping("create/jsonarrray")
    public String createJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add("张三");
        jsonArray.add("李四");
        jsonArray.add("王二麻子");
        System.out.println(jsonArray);
        // 添加对象
        JsonObject object = new JsonObject();
        object.addProperty("name", "韩梅梅");
        object.addProperty("age", 28);
        jsonArray.add(object);
        System.out.println(jsonArray);

        // 打印JsonArray的字符串
        return jsonArray.toString();
    }

}

package com.zgy.learn.bootgson;

import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     *
     * @return
     */
    @RequestMapping("create/jsonobject")
    public String createGson() {
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
}

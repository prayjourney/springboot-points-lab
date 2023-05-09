package com.zgy.learn.bootgson.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author: pray-journey.io
 * @description: Gson对象就是用来转化使用的, Gson的toJson转成json字符串, fromJson转成Object
 * @date: created in 2022/3/16
 * @modified:
 */
@Component
public class Converter {
    @Autowired
    @Qualifier(value = "gsonsettype")
    private Gson gson;

    /**
     * jsonstr转JsonObject
     *
     * @param jsonStr
     * @return
     */
    public JsonObject str2Obj(String jsonStr) {
        JsonObject jsonObject = gson.fromJson(jsonStr, JsonObject.class);
        return jsonObject;
    }

    /**
     * jsonstr转JsonArray
     *
     * @param jsonStr
     * @return
     */
    public JsonArray str2Array(String jsonStr) {
        JsonArray jsonArray = gson.fromJson(jsonStr, JsonArray.class);
        return jsonArray;
    }


}

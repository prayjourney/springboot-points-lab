package com.zgy.learn.xmldeal.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zgy
 * @date 2021/12/3
 */
public class SimpleObject2MapUtil {
    public static Map<String, Object> converter(Object object) {
        Class<?> objectClass = object.getClass();
        Field[] declaredFields = objectClass.getDeclaredFields();
        HashMap<String, Object> map = new HashMap<>();
        try {
            for (Field field : declaredFields) {
                field.setAccessible(true);
                final Object value = field.get(object);
                if (null == value) {
                    map.put(field.getName(), "");
                } else {
                    map.put(field.getName(), value);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

}

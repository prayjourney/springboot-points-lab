package com.zgy.learn.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: pray-journey.io
 * @date: created in 2022/11/5
 * @description:
 */
public class PriceUtil {
    public static Map<String, Integer> price = new HashMap<>();

    public static Map<String, Integer> price() {
        price.put("pen", 20);
        price.put("rice", 3);
        price.put("apple", 6);
        price.put("iPhone", 5400);
        price.put("Car", 120000);
        return price;
    }

}

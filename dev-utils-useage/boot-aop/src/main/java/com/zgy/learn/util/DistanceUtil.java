package com.zgy.learn.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: pray-journey.io
 * @date: created in 2022/11/5
 * @description:
 */
public class DistanceUtil {
    public static Map<String, Integer> distance = new HashMap<>();

    static {
        distance.put("Tokyo", 1230);
        distance.put("Seoul", 740);
        distance.put("Los Angels", 3400);
        distance.put("Osaka", 1100);
        distance.put("Taipei", 930);
        distance.put("Hongkong", 720);
    }

}

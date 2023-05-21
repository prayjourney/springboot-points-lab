package com.zgy.learn.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: pray-journey.io
 * @date: created in 2022/11/5
 * @description:
 */
public class ToolUtil {
    public static final String CAR = "car";
    public static final String BIKE = "bike";
    public static final String AIR_PLANE = "air_plane";

    public static Map<String, Integer> tools = new HashMap<>();

    public static Map<String, Integer> tools() {
        tools.put(BIKE, 15);
        tools.put(CAR, 80);
        tools.put(AIR_PLANE, 800);
        return tools;
    }

}


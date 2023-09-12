package com.zgy.learn.hutooluse.service;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.map.MapUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HutoolService {

    public String now() {
        return DateUtil.now();
    }

    public String formatTime(String type) {
        String pattern;
        switch (type) {
            case "1":
                pattern = DatePattern.CHINESE_DATE_TIME_PATTERN;
                break;
            case "2":
                pattern = DatePattern.NORM_DATETIME_MS_PATTERN;
                break;
            default:
                pattern = DatePattern.NORM_DATETIME_PATTERN;
                break;
        }

        return DateUtil.format(new DateTime(), pattern);
    }

    /**
     * 链式创建map
     *
     * @return
     */
    public Map<String, String> getMap() {
        // 1.不太好
        // Map<String, String> build = MapBuilder.<String, String>create().put("a", "1").put("2", "b").build();
        // 2.推荐
        MapBuilder<String, String> builder = MapUtil.builder(new HashMap<String, String>());
        Map<String, String> build = builder.put("a", "1").put("2", "b").put("3", "c").build();
        return build;
    }

}

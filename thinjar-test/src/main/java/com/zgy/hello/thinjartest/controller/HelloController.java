package com.zgy.hello.thinjartest.controller;

import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author zgy
 * @date 2021/5/8
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/time")
    public String time() {
        Date date = new Date();
        String time = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
        return time;
    }

    @GetMapping("/splice")
    public String splice() {
        String[] s1 = new String[3];
        s1[0] = "张飞";
        s1[1] = "关羽";
        s1[2] = "刘备";
        String str = StringUtils.join(s1, ",");
        return str;

    }
}

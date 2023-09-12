package com.zgy.learn.hutooluse.controller;

import com.zgy.learn.hutooluse.service.HutoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HutoolController {

    @Autowired
    private HutoolService hutoolService;

    @GetMapping("/now")
    public String now() {
        return hutoolService.now();
    }

    @GetMapping("/formatTime")
    public String formatTime(String type) {
        return hutoolService.formatTime(type);
    }

    @GetMapping("/getMap")
    public String getMap() {
        Map<String, String> map = hutoolService.getMap();
        return map.toString();
    }

}

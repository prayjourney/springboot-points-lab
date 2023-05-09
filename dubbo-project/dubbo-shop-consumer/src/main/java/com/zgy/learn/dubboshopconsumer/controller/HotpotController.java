package com.zgy.learn.dubboshopconsumer.controller;

import com.zgy.learn.dubboshopapi.service.HotpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/10/18
 * @modified:
 */
@RestController
@RequestMapping("hotpot")
public class HotpotController {
    // 这个注入的接口, 还是api之中的接口
    @Autowired
    private HotpotService hotpotService;

    @GetMapping("location")
    public String getLocation(String name) {
        return hotpotService.getLocation(name);
    }

    @GetMapping("perprice")
    public Integer getPerPrice(String name) {
        return hotpotService.getPerPrice(name);
    }

    @GetMapping("signboard")
    public List<String> getSignboard(String name) {
        return hotpotService.getSignboard(name);
    }

}

package com.zgy.learn.controller;

import com.zgy.learn.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zgy
 * @date 2022/11/4
 */
@RestController
@RequestMapping("travel")
public class TravelController {
    @Autowired
    private TravelService travelService;

    @GetMapping("destination")
    public String destination(String name) {
        return travelService.destination(name);
    }

    @GetMapping("duration")
    public String duration(String name, String tool) {
        return travelService.duration(name, tool);
    }

}

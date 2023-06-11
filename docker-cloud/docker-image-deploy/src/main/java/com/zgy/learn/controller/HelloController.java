package com.zgy.learn.controller;

import com.zgy.learn.config.PropProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {
    @Autowired
    private PropProperties properties;

    @GetMapping("/name")
    public String name() {
        return properties.getName();
    }

    @GetMapping("/hi")
    public String hi() {
        return properties.getHi();
    }

    @GetMapping("/info")
    public String infos() {
        return properties.getHi() + ", " + properties.getName() + "!";
    }
}

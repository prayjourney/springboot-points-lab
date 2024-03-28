package com.zgy.kafka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping("/okay")
    @ResponseBody
    public String ok() {
        return "i am okay! 我OKAY!";
    }
}

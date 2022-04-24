package com.zgy.learn.beautifulcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: pray-journey.io
 * @date 2022/4/8
 */
@Controller
public class IndexController {
    @RequestMapping(value = {"", "/", "/index", "/home", "/index.htm", "/index.html"})
    public String index() {
        return "index";
    }
}

package com.zgy.learn.ignoreurluppercase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zgy
 * @date 2022/8/5
 */
@Controller
public class IndexController {
    @RequestMapping(value = {"", "/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/Big")
    public String big() {
        return "big";
    }

}

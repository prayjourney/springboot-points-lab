package com.zgy.learn.verificationtianai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zgy
 * @date 2022/4/22
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"", "/", "/index"})
    public String index() {
        return "index";
    }
}

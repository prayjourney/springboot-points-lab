package com.zgy.learn.sslboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: pray-journey.io
 * @date: created in 2021/12/22
 * @description:
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"", "/", "/index"})
    public String index() {
        return "index";
    }
}

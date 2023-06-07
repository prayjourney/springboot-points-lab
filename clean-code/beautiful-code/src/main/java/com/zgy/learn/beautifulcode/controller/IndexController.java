package com.zgy.learn.beautifulcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * get请求无参数
     * curl http://localhost:10118/test
     * curl -X GET http://localhost:10118/test
     */
    @GetMapping(value = "/test")
    @ResponseBody
    public String test() {
        return "test, hello!";
    }

    /**
     * get请求带参数
     * curl 'http://localhost:10118/test-param?age=1&name=zhangsan'
     * curl -X GET 'http://localhost:10118/test-param?age=1&name=zhangsan'
     */
    @GetMapping(value = "/test-param")
    @ResponseBody
    public String testParam(Integer age, String name) {
        return "name: " + name + ", age: " + age;
    }

}

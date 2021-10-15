package com.zgy.learn.hessianclient.controller;

import com.zgy.learn.hessianclient.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zgy
 * @date 2021/10/15
 */
@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/sayHello")
    public String sayHello() {
        return helloService.sayHello();
    }

    @GetMapping("/sayHelloName")
    public String sayHello(String name) {
        return helloService.sayHelloWithName(name);
    }
}

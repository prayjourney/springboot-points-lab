package com.zgy.learn.hessianserver.service.impl;

import com.zgy.learn.hessianserver.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author zgy
 * @date 2021/10/15
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        return "hello";
    }

    @Override
    public String sayHello(String name) {
        return String.format("hello %s", name);
    }

}

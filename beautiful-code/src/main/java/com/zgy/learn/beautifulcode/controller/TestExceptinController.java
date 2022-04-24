package com.zgy.learn.beautifulcode.controller;

import com.zgy.learn.beautifulcode.biz.exception.BizException;
import com.zgy.learn.beautifulcode.biz.exception.MessageCode;
import com.zgy.learn.beautifulcode.biz.exception.SysException;
import com.zgy.learn.beautifulcode.pojo.People;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2022/3/31
 * @modified:
 */
@RestController
@RequestMapping(value = "/test")
public class TestExceptinController {
    @SneakyThrows
    @GetMapping("exception")
    public String exception(Integer id) {
        if (id < 10) {
            throw new Exception("参数id不符合规范");
        }
        return "id 是: " + id;
    }

    @GetMapping("bizException")
    public String bizException(Integer age) {
        if (age < 10) {
            throw new BizException(MessageCode.AGE_ERROR);
        }
        return "age 是: " + age;
    }

    @GetMapping("sysException")
    public String sysException(Integer salary) {
        if (salary < 10) {
            throw new SysException(MessageCode.SYS_ERROR);
        }
        return "age 是: " + salary;
    }

    @GetMapping("nullException")
    public String nullException(People people) {
        String name = people.getName();
        if (null == name) {
            throw new NullPointerException("不允许为null!");
        }
        return "name 是: " + name;
    }

}

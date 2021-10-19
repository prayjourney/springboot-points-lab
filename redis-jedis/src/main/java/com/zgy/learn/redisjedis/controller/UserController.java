package com.zgy.learn.redisjedis.controller;

import cn.hutool.json.JSONObject;
import com.zgy.learn.redisjedis.pojo.User;
import com.zgy.learn.redisjedis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

/**
 * @author zgy
 * @date 2021/10/19
 */
@Validated
@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public int add(@Valid User user) {
        return userService.addUser(user);
    }

    @PostMapping("/delete")
    public int delete(@Positive Integer id) {
        return userService.removeUser(id);
    }

    @GetMapping("/get")
    public JSONObject get(@Positive Integer id) {
        return userService.getUser(id);
    }

}

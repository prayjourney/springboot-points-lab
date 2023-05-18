package com.zgy.learn.securityjwttoken.controller;

import cn.hutool.core.date.DateUtil;
import com.zgy.learn.securityjwttoken.annotation.NotLogin;
import com.zgy.learn.securityjwttoken.service.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private JwtUserService userService;

    /**
     * 自定义的登录接口
     */
    @NotLogin
    @PostMapping("/login")
    public Map login(@RequestParam("username") String username,
                     @RequestParam("password") String password) {
        return userService.login(username, password);
    }

    @NotLogin
    @PostMapping("/time")
    public String time() {
        return DateUtil.now();
    }

    /**
     * 默认权限
     */
    @PreAuthorize("hasAuthority('default')")
    @GetMapping("/test")
    public String testAuthority() {
        return "权限正常, 可以正常访问";
    }

}

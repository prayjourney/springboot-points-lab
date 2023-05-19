package com.zgy.learn.securityjwttoken.controller;

import cn.hutool.core.date.DateUtil;
import com.zgy.learn.securityjwttoken.annotation.NotLogin;
import com.zgy.learn.securityjwttoken.service.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/test01")
    public String testAuthority01() {
        return "权限正常, 可以正常访问";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/test02")
    public String testAuthority02() {
        return "权限正常, 可以正常访问";
    }

    // @NotLogin@PreAuthorize一起, 还是会要权限
    @NotLogin
    @PreAuthorize("hasAuthority('default')")
    @PostMapping("/test03")
    public String testAuthority03() {
        return "权限正常, 可以正常访问";
    }

    @NotLogin
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/test04")
    public String testAuthority04() {
        return "权限正常, 可以正常访问";
    }

    @PostMapping("/info")
    public String info() {
        return "everything";
    }

    @GetMapping("/color")
    public String color() {
        return "红色";
    }

}

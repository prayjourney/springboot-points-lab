package com.zgy.learn.controller;

import com.zgy.learn.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zgy
 * @date 2022/11/4
 */
@RestController
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping("buy")
    public Integer buy(String user, String name, Integer num) {
        return shopService.buy(user, name, num);
    }

}

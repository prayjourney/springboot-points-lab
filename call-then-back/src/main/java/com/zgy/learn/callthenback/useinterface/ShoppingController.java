package com.zgy.learn.callthenback.useinterface;

import com.zgy.learn.callthenback.useinterface.sevice.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2022/5/24
 * @modified:
 */
@RestController
public class ShoppingController {
    @Autowired
    private ShoppingService shoppingService;

    @RequestMapping("/online/shopping")
    public String buy(String name) {
        shoppingService.buy(name);
        return "success";
    }

}

package com.zgy.learn.xmldeal.controller;

import com.zgy.learn.xmldeal.service.EncodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: pray-journey.io
 * @date: created in 2021/12/3
 * @description:
 */
@RestController
public class EncodeController {
    @Resource
    private EncodeService encodeService;

    @GetMapping("encode")
    public String encode(String str) {
        return encodeService.sha1Str(str);
    }

}

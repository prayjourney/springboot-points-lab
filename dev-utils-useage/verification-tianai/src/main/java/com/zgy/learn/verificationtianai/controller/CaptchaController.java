package com.zgy.learn.verificationtianai.controller;

import cloud.tianai.captcha.plugins.secondary.SecondaryVerificationApplication;
import cloud.tianai.captcha.slider.SliderCaptchaApplication;
import cloud.tianai.captcha.template.slider.validator.common.model.dto.SliderCaptchaTrack;
import cloud.tianai.captcha.vo.CaptchaResponse;
import cloud.tianai.captcha.vo.SliderCaptchaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zgy
 * @date 2022/4/22
 */
@RestController
public class CaptchaController {
    @Autowired
    private SliderCaptchaApplication sliderCaptchaApplication;

    // 生成
    @GetMapping("/generate")
    public CaptchaResponse<SliderCaptchaVO> genCaptcha(HttpServletRequest request) {
        CaptchaResponse<SliderCaptchaVO> response = sliderCaptchaApplication.generateSliderCaptcha();
        return response;
    }

    // 检测
    @PostMapping("/check")
    public boolean checkCaptcha(@RequestParam("id") String id,
                                @RequestBody SliderCaptchaTrack sliderCaptchaTrack,
                                HttpServletRequest request) {
        return sliderCaptchaApplication.matching(id, sliderCaptchaTrack);
    }

    /**
     * 二次验证，一般用于机器内部调用，这里为了方便测试
     */
    @GetMapping("/check2")
    @ResponseBody
    public boolean check2Captcha(@RequestParam("id") String id) {
        // 如果开启了二次验证
        if (sliderCaptchaApplication instanceof SecondaryVerificationApplication) {
            return ((SecondaryVerificationApplication) sliderCaptchaApplication).secondaryVerification(id);
        }
        return false;
    }

}

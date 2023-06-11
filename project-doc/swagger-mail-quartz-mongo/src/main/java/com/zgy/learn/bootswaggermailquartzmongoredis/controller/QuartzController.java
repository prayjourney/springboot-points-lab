package com.zgy.learn.bootswaggermailquartzmongoredis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.learn.bootswaggermailquartzmongoredis.service.QuartzService;
import com.zgy.learn.bootswaggermailquartzmongoredis.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: zgy
 * @despcription:
 * @date: Created in 2020/4/15 21:53
 * @modified by:
 */

@Slf4j
@Controller
@RequestMapping("/job")
public class QuartzController {
    @Autowired
    private QuartzService quartzService;

    @RequestMapping(value = "/getNowTime", method = RequestMethod.GET)
    @ResponseBody
    public String getNowTime() throws JsonProcessingException {
        try {
            return JSONUtils.getJsonFromObject(quartzService.getTime());
        } catch (Exception e) {
            log.error("获取时间错误，{}", e.getStackTrace());
            return "获取时间错误";
        }
    }

    @RequestMapping(value = "/sendMailFixed", method = RequestMethod.GET)
    @ResponseBody
    public String sendMailFixed() throws JsonProcessingException {
        try {
            return JSONUtils.getJsonFromObject(quartzService.sendMailFixedTime());
        } catch (Exception e) {
            log.error("发送邮件错误，{}", e.getStackTrace());
            return "发送邮件错误";
        }
    }
}

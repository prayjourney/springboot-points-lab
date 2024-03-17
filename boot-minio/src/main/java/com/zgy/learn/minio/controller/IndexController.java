package com.zgy.learn.minio.controller;

import com.zgy.learn.minio.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class IndexController {
    @Autowired
    private IndexService indexService;

    @GetMapping("")
    public String index() {
        return "index page";
    }

    @GetMapping("/date")
    public String getDate(String userId) {
        if (userId.equals("zhangsan")) {
            log.error("forbidden user: {}", userId);
            return "forbidden user";
        } else if (userId.equals("admin")) {
            log.error("warning user: {}", userId);
            return "warning user";
        }
        return indexService.date(userId);
    }

    @GetMapping("/cacl")
    public Integer cacl(Integer num) {
        try {
            return 100 / num;
        } catch (Exception e) {
            log.debug("error: {}", e.getMessage());
            return -1;
        }
    }

}

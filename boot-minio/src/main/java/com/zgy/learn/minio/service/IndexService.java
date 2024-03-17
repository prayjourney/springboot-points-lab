package com.zgy.learn.minio.service;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IndexService {
    public String date(String userId) {
        log.info("用户:{}, 获取时间, {}", userId, DateUtil.now());
        return DateUtil.now();
    }
}

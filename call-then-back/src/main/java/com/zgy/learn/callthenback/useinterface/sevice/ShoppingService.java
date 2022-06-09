package com.zgy.learn.callthenback.useinterface.sevice;

import com.zgy.learn.callthenback.useinterface.ShoppingCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2022/5/24
 * @modified:
 */
@Slf4j
@Service
public class ShoppingService implements ShoppingCallback {
    @Autowired
    private StoreService storeService;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void buy(String goodsName) {
        log.info("开始下单购买, 时间:{}", LocalDateTime.now().format(DATE_FORMATTER));
        Runnable runnable = () -> {
            storeService.order(goodsName, this);
        };
        Thread thread = new Thread(runnable);
        thread.start();
        log.info("{}下单完成, 等待结果, 时间:{}", goodsName, LocalDateTime.now().format(DATE_FORMATTER));
    }

    @Override
    public void success(String name, String price) {
        log.info("{}购买成功, 价格是:{}, 时间是:{}", name, price, LocalDateTime.now().format(DATE_FORMATTER));
    }

    @Override
    public void fail(String name) {
        log.info("{}购买失败, 时间是:{}", name, LocalDateTime.now().format(DATE_FORMATTER));
    }

}

package com.zgy.learn.callthenback.useinterface.sevice;

import cn.hutool.core.date.DateUtil;
import com.zgy.learn.callthenback.useinterface.ShoppingCallback;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2022/5/24
 * @modified:
 */
@Slf4j
@Service
public class StoreService {
    private static Map<String, String> goodsAndPrice() {
        Map<String, String> map = new HashMap<>();
        map.put("苹果12", "5520");
        map.put("小米11", "4430");
        map.put("Vivo S8", "2999");
        map.put("Mac Air", "5999");
        return map;
    }

    private static Map<String, Integer> goodsAndCount() {
        Map<String, Integer> count = new HashMap<>();
        count.put("苹果12", 0);
        count.put("小米11", 1);
        count.put("Vivo S8", 2);
        count.put("Mac Air", 5);
        return count;
    }

    private int getStock(String name) {
        Map<String, Integer> integerMap = goodsAndCount();
        Integer num = integerMap.get(name);
        if (num == null || num < 0) {
            return -1;
        }
        return num;
    }

    private String getPrice(String name) {
        Map<String, String> goodsAndPrice = goodsAndPrice();
        String price = goodsAndPrice.get(name);
        if (StringUtils.isNotBlank(price)) {
            return price;
        }
        return "-1";
    }

    // 也可以用ShoppingService作为入参, 但是使用接口更好, 面向接口编程
    @SneakyThrows
    public boolean order(String name, ShoppingCallback shoppingCallback) {
        Integer stock = getStock(name);
        if (stock > 0) {
            String price = getPrice(name);
            log.info("开始备货, 时间是:{}", DateUtil.now());
            TimeUnit.SECONDS.sleep(10);
            log.info("备货完成, 时间是:{}, 即将发货, 请耐心等待...", DateUtil.now());
            shoppingCallback.success(name, price);
            return true;
        }
        TimeUnit.SECONDS.sleep(2);
        shoppingCallback.fail(name);
        return false;
    }

}

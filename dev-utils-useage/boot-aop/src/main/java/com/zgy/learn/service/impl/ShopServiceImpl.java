package com.zgy.learn.service.impl;

import com.zgy.learn.handle.BizException;
import com.zgy.learn.handle.MessageCode;
import com.zgy.learn.service.ShopService;
import com.zgy.learn.util.PriceUtil;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author zgy
 * @date 2022/11/4
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Override
    public Integer buy(String user, String name, Integer cashNum) {
        Integer price = PriceUtil.price().get(name);
        if (Objects.isNull(price)) {
            throw new BizException(MessageCode.NO_THINGS_FOUND);
        }
        Integer totalPrice = cashNum * price;
        // return "购买" + cashNum + "个" + name + ", 总价：" + totalPrice.toString();
        return totalPrice;
    }

}

package com.zgy.learn.dubboshopapi.service;

import java.util.List;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/10/17
 * @modified:
 */
public interface HotpotService {
    /**
     * 获取位置
     */
    String getLocation(String name);
    /**
     * 获取人均价格
     */
    Integer getPerPrice(String name);
    /**
     * 获取招牌菜单
     */
    List<String> getSignboard(String name);

}

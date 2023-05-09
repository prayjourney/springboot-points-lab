package com.zgy.learn.dubboshopapi.service;

import com.zgy.learn.dubboshopapi.pojo.Latiao;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/10/17
 * @modified:
 */
public interface LatiaoService {
    /**
     * 生产辣条
     */
    int createLatiao(Latiao latiao);

    /**
     * 辣条检测
     */
    Boolean checkLatiao(Latiao latiao);

}

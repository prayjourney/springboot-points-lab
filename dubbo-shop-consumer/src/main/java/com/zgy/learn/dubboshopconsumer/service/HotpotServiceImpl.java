package com.zgy.learn.dubboshopconsumer.service;

import com.zgy.learn.dubboshopapi.service.HotpotService;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/10/18
 * @modified: 在消费者之中的@Service是普通的Spring的@Service
 */
@Service
public class HotpotServiceImpl implements HotpotService {
    // 这个dubbo引用, 是api之中的接口
    @DubboReference
    HotpotService hotpotService;

    @Override
    public String getLocation(String name) {
        if (StringUtils.isBlank(name)) {
            return "请输入正确的名称";
        }
        return hotpotService.getLocation(name);
    }

    @Override
    public Integer getPerPrice(String name) {
        if (StringUtils.isBlank(name)) {
            return -1;
        }
        return hotpotService.getPerPrice(name);
    }

    @Override
    public List<String> getSignboard(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        return hotpotService.getSignboard(name);
    }

}

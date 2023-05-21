package com.zgy.learn.service.impl;

import com.zgy.learn.handle.BizException;
import com.zgy.learn.handle.MessageCode;
import com.zgy.learn.service.TravelService;
import com.zgy.learn.util.DistanceUtil;
import com.zgy.learn.util.ToolUtil;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author zgy
 * @date 2022/11/4
 */
@Service
public class TravelServiceImpl implements TravelService {
    @Override
    public String destination(String name) {
        Integer distance = DistanceUtil.distance.get(name);
        if (Objects.isNull(distance)) {
            throw new BizException(MessageCode.NO_PALACE_FOUND);
        }
        return "distance is " + distance + "km";
    }

    @Override
    public String duration(String name, String tool) {
        Integer distance = DistanceUtil.distance.get(name);
        if (Objects.isNull(distance)) {
            throw new BizException(MessageCode.NO_PALACE_FOUND);
        }
        Integer speed = ToolUtil.tools().get(tool);
        if (Objects.isNull(speed)) {
            throw new BizException(MessageCode.NO_TOOLS_FOUND);
        }

        Float time = (distance / 1.0f) / speed;
        String info = String.format("去%s乘坐%s需要花%.2f天时间", name, tool, time);
        return info;
    }

}

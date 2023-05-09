package com.zgy.learn.dubboshopprovider.service;

import com.zgy.learn.dubboshopapi.pojo.Hotpot;
import com.zgy.learn.dubboshopapi.service.HotpotService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/10/17
 * @modified: @DubboService表示暴露Dubbo服务
 */
@Component
@DubboService
public class HotpotServiceImpl implements HotpotService {
    List<Hotpot> hotpots = new ArrayList<>();

    private void init() {
        List<String> sign01 = new ArrayList<>();
        sign01.add("毛肚");
        sign01.add("黄喉");
        sign01.add("小酥肉");
        List<String> sign02 = new ArrayList<>();
        sign02.add("鸭血");
        List<String> sign03 = new ArrayList<>();
        sign03.add("吊龙");
        sign03.add("炸豆皮");
        sign03.add("手打牛丸");
        Hotpot hotpot1 = new Hotpot().setName("海底捞").setLocation("金泉宫").setPerPrice(120).setSignboard(sign01);
        Hotpot hotpot2 = new Hotpot().setName("谭鸭血").setLocation("居然之家").setPerPrice(95).setSignboard(sign02);
        Hotpot hotpot3 = new Hotpot().setName("牛麒荟").setLocation("北苑北").setPerPrice(110).setSignboard(sign03);
        hotpots.add(hotpot1);
        hotpots.add(hotpot2);
        hotpots.add(hotpot3);
    }

    @Override
    public String getLocation(String name) {
        if (hotpots.isEmpty()) {
            init();
        }
        List<String> names = names();
        if (!names.contains(name)) {
            return "没有此家店铺";
        }
        Map<String, Map<String, Object>> infos = infos();
        Map<String, Object> map = infos.get(name);
        return map.get("location").toString();
    }

    @Override
    public Integer getPerPrice(String name) {
        if (hotpots.isEmpty()) {
            init();
        }
        List<String> names = names();
        if (!names.contains(name)) {
            return -1;
        }
        Map<String, Map<String, Object>> infos = infos();
        Map<String, Object> map = infos.get(name);
        return Integer.parseInt(map.get("perprice").toString());
    }

    @Override
    public List<String> getSignboard(String name) {
        if (hotpots.isEmpty()) {
            init();
        }
        List<String> names = names();
        if (!names.contains(name)) {
            return null;
        }
        Map<String, Map<String, Object>> infos = infos();
        Map<String, Object> map = infos.get(name);
        return (List<String>) map.get("signboard");
    }

    private List<String> names() {
        return hotpots.stream().map(Hotpot::getName).collect(Collectors.toList());
    }

    private Map<String, Map<String, Object>> infos() {
        Map<String, Map<String, Object>> infos = new HashMap<>();
        hotpots.stream().forEach(x -> {
            Map<String, Object> map = new HashMap<>();
            map.put("location", x.getLocation());
            map.put("perprice", x.getPerPrice());
            map.put("signboard", x.getSignboard());
            map.put("", x.getLocation());
            infos.put(x.getName(), map);
        });
        return infos;
    }

}

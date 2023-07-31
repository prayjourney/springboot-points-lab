package com.zgy.learn.redislettuce.controller;

import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
public class OperateController {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private RedisTemplate<String, String> myStringRedisTemplate;

    /**
     * 指定key的失效时间
     */
    @GetMapping("expire")
    public void expire(String key, long time) {
        redisTemplate.expire(key, time, TimeUnit.MINUTES);
    }

    /**
     * 根据key获取过期时间
     */
    @GetMapping("get-key-expire-time")
    public long getExpire(String key) {
        Long expire = redisTemplate.getExpire(key);
        return expire;
    }

    /**
     * 判断key是否存在
     */
    @GetMapping("haskey")
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 根据key删除redis中的key
     */
    @GetMapping("delete-key")
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 保存和读取String类型的值
     */
    @GetMapping("string-type-values")
    public String stringRedisOperate() {
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForValue().set("key2", "value2");
        redisTemplate.opsForValue().set("key3", "张三");
        redisTemplate.opsForValue().set("key4", "value3", 1, TimeUnit.MINUTES);  // 设置过期时间为1分钟
        // 读取redis数据
        String result1 = redisTemplate.opsForValue().get("key1").toString();
        String result2 = redisTemplate.opsForValue().get("key2").toString();
        String result3 = redisTemplate.opsForValue().get("key3").toString();
        String result4 = redisTemplate.opsForValue().get("key4").toString();
        System.out.println("缓存结果为: result: " + result1 + ", " + result2 + ", " + result3 + ", " + result4);
        return "缓存结果为: result:" + result1 + ", " + result2 + ", " + result3 + ", " + result4;
    }

    /**
     * 保存和读取List类型的值
     */
    @GetMapping("list-type-values")
    public String listRedisOperate() {
        List<String> list1 = new ArrayList<>();
        list1.add("a1");
        list1.add("a2");
        list1.add("a3");
        List<String> list2 = new ArrayList<>();
        list2.add("b1");
        list2.add("张三");
        list2.add("李四");
        List<String> list3 = new ArrayList<>();
        list3.add("b1");
        list3.add("222");
        list3.add("李四");
        redisTemplate.opsForList().leftPush("listkey1", list1);
        redisTemplate.opsForList().rightPush("listkey2", list2);
        redisTemplate.opsForList().rightPush("listkey3", list3);
        List<String> resultList1 = (List<String>) redisTemplate.opsForList().leftPop("listkey1");
        List<String> resultList2 = (List<String>) redisTemplate.opsForList().rightPop("listkey2");
        System.out.println("resultList1:" + resultList1);
        System.out.println("resultList2:" + resultList2);
        return "成功";
    }

    /**
     * 保存和读取Hash类型的值, 操作map, jsonObject等类型的对象
     */
    @GetMapping("hash-type-values")
    public String hashRedisOperate() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");
        redisTemplate.opsForHash().putAll("map1", map);
        Map<Object, Object> resultMap = redisTemplate.opsForHash().entries("map1");
        List<Object> resultMapList = redisTemplate.opsForHash().values("map1");
        Set<Object> resultMapSet = redisTemplate.opsForHash().keys("map1");
        String value = (String) redisTemplate.opsForHash().get("map1", "key1");
        System.out.println("value:" + value);
        System.out.println("resultMapSet:" + resultMapSet);
        System.out.println("resultMap:" + resultMap);
        System.out.println("resultMapList:" + resultMapList);
        return "成功";
    }

    /**
     * 保存和读取Set类型的值
     */
    @GetMapping("set-type-values")
    public String setRedisOperate() {
        SetOperations<String, String> set = myStringRedisTemplate.opsForSet();
        set.add("set-key1", "value1", "hhaha", "zzz智障");
        set.add("set-key1", "value2");
        set.add("set-key1", "value3");
        Set<String> resultSet = myStringRedisTemplate.opsForSet().members("set-key1");
        System.out.println("resultSet:" + resultSet);
        return "resultSet:" + resultSet;
    }

    /**
     * 保存和读取zset
     */
    @GetMapping("zset-type-values")
    public String zsetRedisOperate() {
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1", 9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2", 9.9);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3", 2.2);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        System.out.println(redisTemplate.opsForZSet().add("zset1", tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset1", 0, -1));
        return "成功";
    }

}

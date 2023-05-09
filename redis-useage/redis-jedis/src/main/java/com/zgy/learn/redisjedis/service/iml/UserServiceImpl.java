package com.zgy.learn.redisjedis.service.iml;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zgy.learn.redisjedis.pojo.User;
import com.zgy.learn.redisjedis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author zgy
 * @date 2021/10/19
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JedisPool jedisPool;

    @Override
    public int addUser(User user) {
        Jedis jedis = jedisPool.getResource();
        String key1 = "user:" + user.getId();
        String key2 = "object:user:" + user.getId();
        if (!jedis.exists(key1)) {
            // 对象保存成string
            jedis.set(key1, JSONUtil.toJsonStr(user));
        }
        // 对象保存成hset
        jedis.hset(key2, "id", user.getId() + "");
        jedis.hset(key2, "name", user.getName());
        jedis.hset(key2, "age", user.getAge() + "");
        return 1;
    }

    @Override
    public int removeUser(Integer id) {
        Jedis jedis = jedisPool.getResource();
        String key1 = "user:" + id;
        String key2 = "object:user:" + id;
        if (jedis.exists(key1)) {
            jedis.del(key1);
        }
        if (jedis.exists(key2)) {
            jedis.hdel(key2);
        }
        return 1;
    }

    @Override
    public JSONObject getUser(Integer id) {
        Jedis jedis = jedisPool.getResource();
        String key1 = "user:" + id;
        String key2 = "object:user:" + id;
        if (jedis.exists(key1)) {
            return JSONUtil.parseObj(jedis.get(key1));
        }
        return null;
    }
}

package com.zgy.learn.redisjedis.service;

import cn.hutool.json.JSONObject;
import com.zgy.learn.redisjedis.pojo.User;

/**
 * @author zgy
 * @date 2021/10/19
 */
public interface UserService {
    int addUser(User user);

    int removeUser(Integer id);

    JSONObject getUser(Integer id);

}

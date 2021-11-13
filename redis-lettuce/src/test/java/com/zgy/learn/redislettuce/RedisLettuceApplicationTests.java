package com.zgy.learn.redislettuce;

import com.zgy.learn.redislettuce.pojo.User;
import com.zgy.learn.redislettuce.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisLettuceApplicationTests {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
    }

    @Test
    public void insertUser() {
        User user = new User();
        user.setId(1).setName("张三").setAddress("北京");
        redisUtil.set(user.getId().toString(), user);
        boolean hashKey = redisUtil.hashKey(user.getId().toString());
        System.out.println(hashKey);

    }

    @Test
    public void getUser() {
        boolean hashKey = redisUtil.hashKey("1");
        if (hashKey) {
            String user = (String) redisUtil.get("1");
            System.out.println(user);
        } else {
            System.out.println("没有对象");
        }
    }

}

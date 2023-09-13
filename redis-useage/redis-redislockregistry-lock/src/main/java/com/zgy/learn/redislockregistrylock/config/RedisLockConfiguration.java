package com.zgy.learn.redislockregistrylock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

@Configuration
public class RedisLockConfiguration {
    @Bean(destroyMethod = "destroy")
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
        long defaultExpireTime = 10000L;
        // 注意这里的时间单位是毫秒
        return new RedisLockRegistry(redisConnectionFactory, "redis-lock", defaultExpireTime);

    }
}

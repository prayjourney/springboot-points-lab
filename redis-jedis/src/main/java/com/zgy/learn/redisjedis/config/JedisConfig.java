package com.zgy.learn.redisjedis.config;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author zgy
 * @date 2021/10/19
 */
@Slf4j
@Configuration
public class JedisConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    // 没有设置password的话就不用注入了
    // @Value("${spring.redis.password}")
    // private String password;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;
    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMinIdle(minIdle);

        // JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);

        log.info("jedis pool连接成功, 时间{}，host:{}, port：{}!", DateUtil.now(), host, port);
        return jedisPool;
    }

}

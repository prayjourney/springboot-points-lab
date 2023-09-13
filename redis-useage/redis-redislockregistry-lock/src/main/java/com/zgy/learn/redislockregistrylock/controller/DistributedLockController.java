package com.zgy.learn.redislockregistrylock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Slf4j
@RestController
@RequestMapping("lock")
public class DistributedLockController {
    @Autowired
    private RedisLockRegistry redisLockRegistry;

    @GetMapping("/get")
    public String getLock() {
        Lock lock = redisLockRegistry.obtain("redisLock");
        try {
            // 尝试在指定时间内加锁, 如果已经有其他锁锁住, 获取当前线程不能加锁, 则返回false, 加锁失败, 否则加锁成功则返回true
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                log.info("加锁成功，执行后续代码。线程 ID: {}", Thread.currentThread().getId());
                TimeUnit.SECONDS.sleep(5);
                return "okay";
            }
        } catch (InterruptedException e) {
            log.error("obtain lock error:{}, 线程 ID: {}", e, Thread.currentThread().getId());
        } finally {
            // 解锁
            lock.unlock();
        }
        return "okay";
    }

}
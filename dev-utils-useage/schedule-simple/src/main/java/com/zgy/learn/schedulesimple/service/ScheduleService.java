package com.zgy.learn.schedulesimple.service;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScheduleService {
    /**
     * 按照固定速率去执行, 10s执行1次
     * <p>
     * [   scheduling-1] c.z.l.s.service.ScheduleService          : task1 execute, time:2023-08-03 23:51:22
     * [   scheduling-1] c.z.l.s.service.ScheduleService          : task1 execute, time:2023-08-03 23:51:32
     * [   scheduling-1] c.z.l.s.service.ScheduleService          : task1 execute, time:2023-08-03 23:51:42
     */
    @Scheduled(initialDelay = 1000, fixedRate = 10 * 1000)
    public void task1() {
        log.info("task1 execute, time:{}", DateUtil.now());

    }

    /**
     * 按照固定速率执行, 但是发生阻塞, 等待阻塞完毕之后, 立即执行下次任务, 后续争取按照固定速率执行, 但是无法保证
     * <p>
     * [   scheduling-1] c.z.l.s.service.ScheduleService          : task1-Delay execute, time:2023-08-03 23:56:38
     * [   scheduling-1] c.z.l.s.service.ScheduleService          : task1-Delay execute, time:2023-08-03 23:56:44
     * [   scheduling-1] c.z.l.s.service.ScheduleService          : task1-Delay execute, time:2023-08-03 23:56:50
     * [   scheduling-1] c.z.l.s.service.ScheduleService          : task1-Delay execute, time:2023-08-03 23:56:56
     *
     * @throws InterruptedException
     */
    @Scheduled(initialDelay = 1000, fixedRate = 1 * 1000)
    public void task1Delay() throws InterruptedException {
        log.info("task1-Delay execute, time:{}", DateUtil.now());
        Thread.sleep(6000);
    }

    /**
     * 两个任务之间延迟3s执行, 任务堆积也无所谓, 仍然是上一个执行完之后再等3s继续执行
     */
    // @Scheduled(fixedDelay = 3 * 1000)
    public void task2() throws InterruptedException {
        log.info("task2 execute, time:{}", DateUtil.now());
        Thread.sleep(6000);
    }

    /**
     * 使用corn表达式, 下面是5s执行一次
     * <p>
     * [   scheduling-1] c.z.l.s.service.ScheduleService          : task3 execute, time:2023-08-04 00:57:40
     * [   scheduling-1] c.z.l.s.service.ScheduleService          : task3 execute, time:2023-08-04 00:57:45
     * [   scheduling-1] c.z.l.s.service.ScheduleService          : task3 execute, time:2023-08-04 00:57:50
     */
    //@Scheduled(cron = "0/5 * * * * ?")
    public void task3() {
        log.info("task3 execute, time:{}", DateUtil.now());
    }

    /**
     * 使用corn表达式, 预计2s执行一次, 但是任务延迟了6s, 所以是任务执行完之后, 立即执行下一次, 尽量保持2s执行一次的计划, 但不保证
     * <p>
     * [   scheduling-1] c.z.l.s.service.ScheduleService          : task1-Delay execute, time:2023-08-04 01:00:50
     * [   scheduling-1] c.z.l.s.service.ScheduleService          : task1-Delay execute, time:2023-08-04 01:00:58
     * [   scheduling-1] c.z.l.s.service.ScheduleService          : task1-Delay execute, time:2023-08-04 01:01:06
     *
     * @throws InterruptedException
     */
    @Scheduled(cron = "0/2 * * * * ?")
    public void task3Delay() throws InterruptedException {
        log.info("task3-Delay execute, time:{}", DateUtil.now());
        Thread.sleep(6000);
    }

}

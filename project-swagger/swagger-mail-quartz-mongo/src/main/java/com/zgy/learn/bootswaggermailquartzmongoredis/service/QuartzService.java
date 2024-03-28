package com.zgy.learn.bootswaggermailquartzmongoredis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: zgy
 * @despcription: 定时任务
 * @date: Created in 2020/4/15 21:53
 * @modified by:
 */
@Slf4j
@Service
public class QuartzService {
    //Logger log = LoggerFactory.getLogger(QuartzService.class);
    private String timeFormatter = "yyyy-MM-dd HH:mm:ss";
    @Autowired
    private MailService mailService;

    @Scheduled(cron = "0 * * * * ?") //每一分钟都打印一次时间
    public String getTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String nowTime = localDateTime.format(DateTimeFormatter.ofPattern(timeFormatter)).toString();
        log.info("print now time : {}", nowTime);
        return nowTime;

    }

    // 秒，分，小时，日，月，星期，年 //一共是7位，星期一般不写
    @Scheduled(cron = "0 0 12 15 2 ?") //修改成每年2月14日发送一份邮件，祝福生日快乐
    //@Scheduled(cron = "0 */3 * * * ?") //每3分钟发送一份邮件
    // @Scheduled(cron = "0 * * * * ?") //每一分钟都打印一次时间
    public String sendMailFixedTime() {
        String to = "renjiaxin@126.com";
        String subject = "你好";
        String content = "最光阴你好，生日快乐！";
        mailService.sendMailWithoutAppendix(to, subject, content);
        log.info("邮件发送成功！{}" + LocalDateTime.now().toString());
        return "发送成功!";
    }
}

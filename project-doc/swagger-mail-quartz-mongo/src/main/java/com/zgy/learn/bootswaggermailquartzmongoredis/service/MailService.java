package com.zgy.learn.bootswaggermailquartzmongoredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author: zgy
 * @despcription:
 * @date: Created in 2020/4/15 21:53
 * @modified by:
 */
@Service
public class MailService {
    @Autowired(required = false)
    JavaMailSender sender;
    public String from = "2247359268@qq.com";

    /**
     * 简单文本邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    public void sendMailWithoutAppendix(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        sender.send(message);
    }

    /**
     * 带有附件的邮件发送
     */
    public void sendMailWithAppendix() {
    }
}

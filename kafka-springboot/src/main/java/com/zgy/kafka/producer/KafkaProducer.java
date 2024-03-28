package com.zgy.kafka.producer;

import com.alibaba.fastjson.JSONObject;
import com.zgy.kafka.bean.MyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class KafkaProducer {

    private static Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    //发送消息方法
    public void send() {
        for (int i = 0; i < 5; i++) {
            MyMessage message = new MyMessage();
            message.setId(String.valueOf(System.currentTimeMillis()));
            message.setMessageContent(UUID.randomUUID().toString() + "---" + i);
            message.setSendTime(new Date());
            logger.info("发送消息 ----->>>>>  message = {}", JSONObject.toJSONString(message));
            kafkaTemplate.send("hello", JSONObject.toJSONString(message));
        }
    }


}

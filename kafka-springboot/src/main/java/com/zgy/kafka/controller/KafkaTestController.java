package com.zgy.kafka.controller;

import com.zgy.kafka.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KafkaTestController {

    @Autowired
    private KafkaProducer producer;

    @RequestMapping("/testSendMsg")
    @ResponseBody
    public String testSendMsg() {
        producer.send();
        return "success";
    }

}


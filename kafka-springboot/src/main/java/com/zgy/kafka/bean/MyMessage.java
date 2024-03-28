package com.zgy.kafka.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Component
public class MyMessage {
    private String id;
    private String messageContent;
    private Date sendTime;
}

package com.zgy.learn.xmldeal.service;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.XmlUtil;
import com.zgy.learn.xmldeal.pojo.Student;
import com.zgy.learn.xmldeal.utils.SimpleObject2MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import java.util.Map;

/**
 * @author zgy
 * @date 2021/12/3
 */
@Slf4j
@Service
public class XmlService {
    String strXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <Notification xmlns=\"http://mns.aliyuncs.com/doc/v1/\"> <TopicOwner>1692545896541241</TopicOwner> <TopicName>MyTopic</TopicName> <Subscriber>1692545896541241</Subscriber> <SubscriptionName>bing-test3</SubscriptionName> <MessageId>C39FB8C345BBFBA8-1-1687F6FAADD-200000015</MessageId> <MessageMD5>CAA1E9F5E9F854ACD8297B100BF8CCF9</MessageMD5> <Message>{\"jobId\":\"2384a4d89b1d4f1e869559e2ff8c9fad\",\"requestId\":\"639D1D03-1557-4AD7-9AD7-691F02834516\",\"Type\":\"Transcode\",\"state\":\"Success\",\"type\":\"Transcode\",\"State\":\"Success\",\"JobId\":\"2384a4d89b1d4f1e869559e2ff8c9fad\",\"RequestId\":\"639D1D03-1557-4AD7-9AD7-691F02834516\"}</Message> <PublishTime>1548326251229</PublishTime> </Notification>";

    // 解析xml-str成为Map
    public Map<String, Object> parseXml() {
        Map<String, Object> xmlToMap = XmlUtil.xmlToMap(strXML);
        return xmlToMap;
    }

    // 生成xml
    public String createXml(Student student, String rootName) {
        Map<String, Object> studentMap = SimpleObject2MapUtil.converter(student);
        Document document = XmlUtil.createXml();
        return document.toString();
    }
}

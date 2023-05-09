package com.zgy.learn.xmldeal.service;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author: pray-journey.io
 * @date: created in 2021/12/4
 * @description:
 */
@Service
@Slf4j
public class Dom4jXmlService {
    /**
     * 从文件解析xml文档
     *
     * @param file
     */
    public void parseXmlFromFile(File file) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);

            log.info("Root element :" + document.getRootElement().getName());
            Element classElement = document.getRootElement();
            List<Node> nodes = document.selectNodes("/class/student");
            for (Node node : nodes) {
                log.info("\nCurrent Element :" + node.getName());
                log.info("Student roll no : " + node.valueOf("@rollno"));
                log.info("First Name : " + node.selectSingleNode("firstname").getText());
                log.info("Last Name : " + node.selectSingleNode("lastname").getText());
                log.info("First Name : " + node.selectSingleNode("nickname").getText());
                log.info("Marks : " + node.selectSingleNode("marks").getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从字符串解析xml文档
     *
     * @param str
     */
    public void parseXmlFromString(String str) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new ByteArrayInputStream(str.getBytes("UTF-8")));

            log.info("Root element :" + document.getRootElement().getName());
            Element classElement = document.getRootElement();
            List<Node> nodes = document.selectNodes("/Notification");
            for (Node node : nodes) {
                log.info("\nCurrent Element :" + node.getName());
                log.info("TopicOwner : " + ((DefaultElement) node).element("TopicOwner").getText());
                log.info("TopicName : " + ((DefaultElement) node).element("TopicName").getText());
                log.info("Subscriber : " + ((DefaultElement) node).element("Subscriber").getText());
                log.info("SubscriptionName : " + ((DefaultElement) node).element("SubscriptionName").getText());
                log.info("MessageId : " + ((DefaultElement) node).element("MessageId").getText());
                log.info("MessageMD5 : " + ((DefaultElement) node).element("MessageMD5").getText());
                log.info("Message : " + ((DefaultElement) node).element("Message").getText());
                log.info("PublishTime : " + ((DefaultElement) node).element("PublishTime").getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}

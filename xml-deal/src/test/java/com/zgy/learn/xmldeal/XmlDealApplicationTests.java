package com.zgy.learn.xmldeal;

import com.zgy.learn.xmldeal.pojo.Student;
import com.zgy.learn.xmldeal.pojo.Teacher;
import com.zgy.learn.xmldeal.service.Dom4jXmlService;
import com.zgy.learn.xmldeal.service.Pojo2XmlService;
import com.zgy.learn.xmldeal.service.XmlService;
import com.zgy.learn.xmldeal.utils.SimpleObject2MapUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class XmlDealApplicationTests {

    @Resource
    private XmlService xmlService;
    @Resource
    private Dom4jXmlService dom4jXmlService;
    @Resource
    private Pojo2XmlService pojo2XmlService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testXMLStrMap() {
        Map<String, Object> stringObjectMap = xmlService.parseXml();
        Set<String> strings = stringObjectMap.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = stringObjectMap.get(key);
            System.out.println("key: " + key + ", value: " + value);
        }
    }

    @Test
    public void testCreateXMLByObject() {
        Student student = new Student();
        student.setId(1).setName("张三").setAge(12).setGender("男").setGrade("初二二班").setHome("红花堰");
        String xmlStr = xmlService.createXml(student, "测试");
        System.out.println(xmlStr);
    }

    @Test
    public void testSimpleObj2Map() {
        Student student = new Student();
        student.setId(1).setName("张三").setAge(12).setGender("男").setGrade("初二二班").setHome("红花堰");
        Map<String, Object> converter = SimpleObject2MapUtil.converter(student);
        Iterator<Map.Entry<String, Object>> iterator = converter.entrySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next().getKey();
            String value = iterator.next().getValue().toString();
            System.out.printf("key: %s, value: %s\t", key, value);
        }
    }

    @Test
    public void testParseXmlFromFile() throws IOException {
        File file = ResourceUtils.getFile("classpath:xml-demo.xml");
        dom4jXmlService.parseXmlFromFile(file);
    }

    @Test
    public void testParseXmlFromString() throws IOException {
        String strXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <Notification xmlns=\"http://mns.aliyuncs.com/doc/v1/\"> <TopicOwner>1692545896541241</TopicOwner> <TopicName>MyTopic</TopicName> <Subscriber>1692545896541241</Subscriber> <SubscriptionName>bing-test3</SubscriptionName> <MessageId>C39FB8C345BBFBA8-1-1687F6FAADD-200000015</MessageId> <MessageMD5>CAA1E9F5E9F854ACD8297B100BF8CCF9</MessageMD5> <Message>{\"jobId\":\"2384a4d89b1d4f1e869559e2ff8c9fad\",\"requestId\":\"639D1D03-1557-4AD7-9AD7-691F02834516\",\"Type\":\"Transcode\",\"state\":\"Success\",\"type\":\"Transcode\",\"State\":\"Success\",\"JobId\":\"2384a4d89b1d4f1e869559e2ff8c9fad\",\"RequestId\":\"639D1D03-1557-4AD7-9AD7-691F02834516\"}</Message> <PublishTime>1548326251229</PublishTime> </Notification>";
        dom4jXmlService.parseXmlFromString(strXML);
    }

    @Test
    public void testObj2Xml() {
        Teacher teacher = new Teacher();
        teacher.setId(1).setAge(22).setGender("男").setHome("北京").setMajor("数学").setName("张三");
        String xmlStr = pojo2XmlService.obj2Xml(teacher);
        System.out.println(xmlStr);
    }

}

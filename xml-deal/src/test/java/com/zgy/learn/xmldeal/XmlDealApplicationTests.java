package com.zgy.learn.xmldeal;

import com.zgy.learn.xmldeal.pojo.Student;
import com.zgy.learn.xmldeal.service.XmlService;
import com.zgy.learn.xmldeal.utils.SimpleObject2MapUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class XmlDealApplicationTests {

    @Resource
    private XmlService xmlService;

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

}

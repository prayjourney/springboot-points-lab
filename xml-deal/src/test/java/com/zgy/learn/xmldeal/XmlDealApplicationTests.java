package com.zgy.learn.xmldeal;

import com.zgy.learn.xmldeal.service.XmlService;
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
    public void testXmlstrMap() {
        Map<String, Object> stringObjectMap = xmlService.parseXml();
        Set<String> strings = stringObjectMap.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = stringObjectMap.get(key);
            System.out.println("key: " + key + ", value: " + value);
        }
    }

}

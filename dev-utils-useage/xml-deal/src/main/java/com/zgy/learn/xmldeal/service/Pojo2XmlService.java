package com.zgy.learn.xmldeal.service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.zgy.learn.xmldeal.pojo.Teacher;
import org.springframework.stereotype.Service;

/**
 * @author: pray-journey.io
 * @date: created in 2021/12/4
 * @description:
 */
@Service
public class Pojo2XmlService {
    /**
     * obj转为xml
     *
     * @param teacher
     * @return
     */
    public String obj2Xml(Teacher teacher) {
        XStream x = new XStream();
        // 尽量限制所需的最低权限 这条语句解决该问题
        x.addPermission(AnyTypePermission.ANY);
        x.processAnnotations(Teacher.class);
        String str = x.toXML(teacher);
        Teacher t = (Teacher) x.fromXML(str);
        System.out.println(t.toString());
        return str;
    }

}

package com.zgy.learn.xmldeal.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author: pray-journey.io
 * @date: created in 2021/12/4
 * @description:
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@XStreamAlias("Person")
public class Teacher {
    @XStreamAlias("id")
    @XStreamAsAttribute
    private Integer id;

    @XStreamAlias("name")
    private String name;

    @XStreamAlias("gender")
    private String gender;

    @XStreamAlias("age")
    private Integer age;

    @XStreamAlias("homeAddress")
    private String home;

    @XStreamAlias("major")
    private String major;
}

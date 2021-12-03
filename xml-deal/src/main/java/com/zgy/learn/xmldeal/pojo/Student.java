package com.zgy.learn.xmldeal.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author zgy
 * @date 2021/12/3
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Student {
    private Integer id;
    private String name;
    private String gender;
    private Integer age;
    private String home;
    private String grade;
}

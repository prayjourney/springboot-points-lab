package com.zgy.learn.mapstructlearn.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/9/27
 * @modified:
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Student {
    private Integer id;
    private String name;
    private Integer gender;
    private Date birthday;
    private String home;

}

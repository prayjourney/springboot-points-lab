package com.zgy.learn.beautifulcleancode.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/9/28
 * @modified:
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class People {
    private Integer id;
    private String name;
    private Integer salary;
    private Date birthday;
    private String telephone;
    private String email;
    private String blogPage;
    private String address;

}

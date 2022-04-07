package com.zgy.learn.beautifulcleancode.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
public class PeopleVo {
    private Integer id;
    private String name;
    private Integer salary;
    private String birthday;
    private String telephone;
    private String email;
    private String blogPage;
    private String address;

}

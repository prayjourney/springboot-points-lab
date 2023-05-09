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
public class Room {
    private Integer id;
    private Date time;
    private Student student;
}

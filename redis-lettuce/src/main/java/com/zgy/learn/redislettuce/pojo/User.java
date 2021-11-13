package com.zgy.learn.redislettuce.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: pray-journey.io
 * @date: created in 2021/11/13
 * @description:
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String address;

}

package com.zgy.learn.bootgson.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2022/3/15
 * @modified:
 */
@Getter
@Setter
@Accessors(chain = true)
public class Monkey implements Serializable {
    public static final long serialVersionUID = 1L;

    private Integer id;
    private Integer age;
    private Integer gender;
    private String name;
    /**
     * 品种
     */
    private String kind;

}

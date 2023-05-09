package com.zgy.learn.bootguava;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: pray-journey.io
 * @description: 宠物主人
 * @date: created in 2022/3/15
 * @modified:
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class PetHost implements Serializable {
    public static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private Integer age;
    private Integer gender;
    private String address;
    private Date birthday;

}

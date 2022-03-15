package com.zgy.learn.bootgson.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author: pray-journey.io
 * @description: 柴犬
 * @date: created in 2022/3/15
 * @modified:
 */
@Getter
@Setter
@Accessors(chain = true)
public class ShibaInu implements Serializable {
    public static final long serialVersionUID = 1L;

    private Integer id;
    private Integer age;
    private Integer gender;
    private String name;
    /**
     * 原产地
     */
    private String origin;
    /**
     * 宠物主人
     */
    private List<PetHost> petHosts;

}

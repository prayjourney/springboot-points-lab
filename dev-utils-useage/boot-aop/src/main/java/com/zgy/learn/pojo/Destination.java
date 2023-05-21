package com.zgy.learn.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zgy
 * @date 2022/11/4
 * @desc 旅游目的地
 */
@Data
@Accessors(chain = true)
public class Destination implements Serializable {
    public static final long serialVersionUID = 1L;
    private String name;
    private Integer distance;
}

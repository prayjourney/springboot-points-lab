package com.zgy.learn.bootgson.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: pray-journey.io
 * @description: 刺猬
 * @date: created in 2022/3/15
 * @modified:
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Hedgehog implements Serializable {
    public static final long serialVersionUID = 1L;

    private Integer id;
    private Integer age;
    private String name;
    /*属性重命名， 命名为origin_address, 原先是originAddress*/
    @SerializedName(value = "origin_address")
    private String originAddress;
    @SerializedName(value = "catch_time")
    private Date catchTime;

}

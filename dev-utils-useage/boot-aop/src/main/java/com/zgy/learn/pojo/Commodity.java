package com.zgy.learn.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zgy
 * @date 2022/11/4
 * @desc 购买的商品
 */
@Data
@Accessors(chain = true)
public class Commodity implements Serializable {
    public static final long serialVersionUID = 1L;
    private String name;
    private String type;
    private Integer price;
    private Date createTime;

}

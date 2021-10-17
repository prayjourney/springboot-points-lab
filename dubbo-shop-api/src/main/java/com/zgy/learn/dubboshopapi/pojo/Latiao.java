package com.zgy.learn.dubboshopapi.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/10/17
 * @modified:
 */
@Data
@ToString
public class Latiao implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 名称
     */
    private String name;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 生产日期
     */
    private Date productDate;

    /**
     * 保存时间
     */
    private Integer day;

}

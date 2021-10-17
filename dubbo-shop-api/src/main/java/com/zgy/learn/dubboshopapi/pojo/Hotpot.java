package com.zgy.learn.dubboshopapi.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/10/17
 * @modified:
 */
@Data
@ToString
public class Hotpot implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 名称
     */
    private String name;
    /**
     * 位置
     */
    private String location;
    /**
     * 人均价格
     */
    private Integer perPrice;
    /**
     * 招牌菜品
     */
    private List<String> signboard;

}

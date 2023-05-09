package com.zgy.learn.mapstructlearn.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
public class StudentVo {
    private Integer studentId;
    private String name;
    private String gender;
    // 在Mapper之中进行了转换
    private String birthday;
    private String homeLocation;

}

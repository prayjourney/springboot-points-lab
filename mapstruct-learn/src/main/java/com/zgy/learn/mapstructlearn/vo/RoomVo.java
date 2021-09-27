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
public class RoomVo {
    private Integer id;
    private String createTime;
    private Integer studentId;
    private String studentName;
    private String ext;
}

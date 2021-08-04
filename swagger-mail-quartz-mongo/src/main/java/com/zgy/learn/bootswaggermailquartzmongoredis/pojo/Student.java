package com.zgy.learn.bootswaggermailquartzmongoredis.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "学生的实体类", description = "学生的实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @ApiModelProperty(value = "学生id", notes = "学生id")
    private Integer stId;
    @ApiModelProperty(value = "学生姓名", notes = "学生姓名")
    private String stName;
    @ApiModelProperty(value = "学生性别", notes = "学生性别")
    private String stGender;
    @ApiModelProperty(value = "学生年级", notes = "学生年级")
    private String stGrade;
    @ApiModelProperty(value = "学生班级", notes = "学生班级")
    private String stClass;
}

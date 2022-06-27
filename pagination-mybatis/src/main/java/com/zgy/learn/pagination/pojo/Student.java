package com.zgy.learn.pagination.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 学生id
     */
    private Integer stId;
    /**
     * 学生姓名
     */
    private String stName;
    /**
     * 学生年龄
     */
    private Integer stAge;
    /**
     * 学生电话
     */
    private String stPhoneNo;
    /**
     * 学生性别
     */
    private String stGender;
    /**
     * 班级
     */
    private String stClass;
    /**
     * 家庭住址
     */
    private String stHome;
    /**
     * 双亲Id
     */
    private Integer stParentsId;

}
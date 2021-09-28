package com.zgy.learn.beautifulcleancode.pojo.req;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/9/28
 * @modified:
 */
public class PeopleReq {
    @Min(value = 0, message = "不能小于0")
    private Integer id;
    @NotNull(message = "不能为空")
    private String name;
    @Positive(message = "必须大于0")
    private Integer salary;
    @Past(message = "不是一个过去的时间")
    private Date birthday;
    @Length(min = 8, max = 16, message = "电话无效")
    private String telephone;
    @Email(message = "邮箱无效")
    private String email;
    @URL(message = "地址无效")
    private String blogPage;
    @Size(min = 5, max = 100, message = "地址无效")
    private String address;

}

package com.zgy.learn.beautifulcleancode.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/9/28
 * @modified:
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@RequiredArgsConstructor // 生成指定字段的构造器， @NonNull / final都可使其起作用
public class People {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private Integer salary;
    private Date birthday;
    private String telephone;
    private String email;
    private String blogPage;
    private String address;

}

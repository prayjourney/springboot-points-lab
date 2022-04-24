package com.zgy.learn.beautifulcode.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2022/4/5
 * @modified:
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@ToString
public class User {
    private Integer id;
    private String name;
    private Integer salary;
    private String telephone;
    private String email;
    private String address;
}

package com.zgy.learn.redisjedis.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * @author zgy
 * @date 2021/10/19
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空")
    @Positive(message = "id必须大于0")
    private Integer id;

    @NotBlank(message = "name不可以为空白")
    private String name;

    @NotNull(message = "age不能为空")
    @Positive(message = "age必须大于0")
    private Integer age;

}


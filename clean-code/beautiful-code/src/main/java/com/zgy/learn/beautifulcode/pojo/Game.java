package com.zgy.learn.beautifulcode.pojo;

import lombok.*;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2022/4/23
 * @modified:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor // 配合@NonNull, final使用, 部分参数的构造器
public class Game {
    @NonNull
    private String name;
    @NonNull
    private String type;
    private String description;

}

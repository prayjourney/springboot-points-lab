package com.zgy.learn.beautifulcode.pojo;

import lombok.Data;
import lombok.NonNull;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2022/4/23
 * @modified:
 */
@Data
public class Game {
    @NonNull
    private String name;
    @NonNull
    private String type;
    private String description;

}

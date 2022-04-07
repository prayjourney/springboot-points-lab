package com.zgy.learn.bigfileupzipdown.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/10/6
 * @modified:
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class FileChunkRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String md5;
    private Integer chunk;
    /**
     * 0-fail, 1-okay
     */
    private Integer uploadStatus;

}

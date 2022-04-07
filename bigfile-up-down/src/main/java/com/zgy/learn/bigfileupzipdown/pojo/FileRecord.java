package com.zgy.learn.bigfileupzipdown.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
public class FileRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String fileName;
    private String fileMd5;
    private String filePath;
    private String fileSize;
    /**
     * 0-fail, 1-okay
     */
    private Integer uploadStatus;
    private Date createTime;
    private Date updateTime;

}

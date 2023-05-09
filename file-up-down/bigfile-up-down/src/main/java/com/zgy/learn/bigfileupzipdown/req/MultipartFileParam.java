package com.zgy.learn.bigfileupzipdown.req;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class MultipartFileParam {
    /**
     * 是否分片
     */
    @NotNull(message = "是否分片不能为空")
    private boolean chunkFlag;

    /**
     * 当前为第几块分片
     */
    private int chunk;

    /**
     * 总分片数量
     */
    private int totalChunk;

    /**
     * 文件总大小, 单位是byte
     */
    private long totalSize;

    /**
     * 文件名
     */
    @NotBlank(message = "文件名不能为空")
    private String name;

    /**
     * 文件
     */
    @NotNull(message = "文件不能为空")
    private MultipartFile file;

    /**
     * md5值
     */
    @NotBlank(message = "文件md5值不能为空")
    private String md5;

}

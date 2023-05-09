package com.zgy.learn.bigfileupzipdown.controller;

import com.zgy.learn.bigfileupzipdown.req.MultipartFileParam;
import com.zgy.learn.bigfileupzipdown.service.FileUploadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/10/6
 * @modified:
 */
@RestController
@RequestMapping("file")
public class FileUploadController {
    @Resource
    private FileUploadService fileUploadService;

    /**
     * 文件上传
     *
     * @param fileParam
     * @return
     */
    @PostMapping(value = "upload")
    public String upload(MultipartFileParam fileParam) throws IOException {
        return fileUploadService.fileUpload(fileParam);
    }

    /**
     * 秒传检测
     *
     * @param md5
     * @return
     */
    @GetMapping(value = "check")
    public String check(String md5) {
        return fileUploadService.check(md5);
    }

}

package com.zgy.learn.minio.controller;

import com.zgy.learn.minio.util.MinioUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Objects;

@RestController
@RequestMapping(value = "/minio")
public class MinioController {
    private MinioUtil minioUtil;

    @Value("${minio.default-bucket}")
    private String defaultBucket;

    @Value("${minio.expire-time}")
    private Integer expireTime;


    public MinioController(@Autowired MinioUtil minioUtil) {
        this.minioUtil = minioUtil;
    }


    @PostMapping(value = "/uploadFile")
    public String fileUpload(@RequestParam MultipartFile uploadFile, @RequestParam String bucket,
                             @RequestParam(required = false) String objectName) throws Exception {
        Boolean bucketExists = minioUtil.bucketExists(bucket);
        if (!bucketExists) {
            return "bucket " + bucket + " 不存在, 请先创建好!";
        }
        if (objectName != null) {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucket, objectName + "/" + uploadFile.getOriginalFilename());
        } else {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucket, uploadFile.getOriginalFilename());
        }
        return "上传成功!";
    }


    @GetMapping(value = "/downloadFile")
    public void downloadFile(@RequestParam String bucket, @RequestParam String objectName, @RequestParam String fileName,
                             HttpServletResponse response) throws Exception {
        // 文件名是包括objectName和自己的名字的, 比如mybucket下面，测试文件夹下的a.jpg
        InputStream stream = minioUtil.download(bucket, objectName + "/" + fileName);
        ServletOutputStream output = response.getOutputStream();
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(objectName.substring(objectName.lastIndexOf("/") + 1), "UTF-8"));
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        IOUtils.copy(stream, output);
    }


    @GetMapping(value = "/deleteFile")
    public String deleteFile(@RequestParam String bucket, @RequestParam String objectName, @RequestParam String fileName) throws Exception {
        minioUtil.deleteObject(bucket, objectName + "/" + fileName);
        return "删除成功!";
    }


    @GetMapping(value = "/createBucket")
    public String createBucket(String bucket) throws Exception {
        String bucketName = bucket;
        if (StringUtils.isBlank(bucket)) {
            bucketName = defaultBucket;
        }
        if (minioUtil.bucketExists(bucketName)) {
            return "名字为: " + bucketName + " 的bucket已经存在, 请检查!";
        }
        minioUtil.createBucket(bucketName);
        return "创建成功!";
    }

    @PostMapping(value = "/deleteBucket")
    public String deleteBucket(@RequestParam String bucket) throws Exception {
        minioUtil.deleteBucket(bucket);
        return "删除成功!";
    }


    @GetMapping("/copyObject")
    public String copyObject(@RequestParam String sourceBucket, @RequestParam String
            sourceObject, @RequestParam String targetBucket, @RequestParam String targetObject) throws Exception {
        minioUtil.copyObject(sourceBucket, sourceObject, targetBucket, targetObject);
        return "复制成功!";
    }

    @GetMapping("/getObjectInfo")
    public String getObjectInfo(@RequestParam String bucket, @RequestParam String objectName) throws Exception {
        String objectInfo = minioUtil.getObjectInfo(bucket, objectName);
        return objectInfo;
    }

    @GetMapping("/getPresignedObjectUrl")
    public String getPresignedObjectUrl(@RequestParam String bucket, @RequestParam String
            objectName, @RequestParam Integer expires) throws Exception {
        if (Objects.isNull(expires) || expires <= 0) {
            expires = expireTime;
        }
        return minioUtil.getPresignedObjectUrl(bucket, objectName, expires);
    }

}

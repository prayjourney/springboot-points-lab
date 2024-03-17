package com.zgy.learn.minio.util;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class MinioUtil {
    @Resource
    private MinioClient minioClient;


    /**
     * 获取全部bucket
     */
    public List<Bucket> getAllBuckets() {
        try {
            List<Bucket> buckets = minioClient.listBuckets();
            return buckets;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查看存储bucket是否存在
     */
    public Boolean bucketExists(String bucketName) {
        try {
            return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 创建一个桶
     */
    public void createBucket(String bucket) throws Exception {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
    }


    /**
     * 删除一个桶
     */
    public void deleteBucket(String bucket) throws Exception {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucket).build());
    }


    /**
     * 上传一个文件
     */
    public void uploadFile(InputStream stream, String bucket, String objectName) throws Exception {
        minioClient.putObject(PutObjectArgs.builder().bucket(bucket).object(objectName)
                .stream(stream, -1, 10485760).build());
    }


    /**
     * 下载一个文件
     */
    public InputStream download(String bucket, String objectName) throws Exception {
        InputStream stream = minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(objectName).build());
        return stream;
    }


    /**
     * 删除一个对象
     */
    public void deleteObject(String bucket, String objectName) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(objectName).build());
    }


    /**
     * 复制文件
     */
    public void copyObject(String sourceBucket, String sourceObject, String targetBucket, String targetObject) throws Exception {
        this.createBucket(targetBucket);
        minioClient.copyObject(CopyObjectArgs.builder().bucket(targetBucket).object(targetObject)
                .source(CopySource.builder().bucket(sourceBucket).object(sourceObject).build()).build());
    }


    /**
     * 获取文件信息
     */
    public String getObjectInfo(String bucket, String objectName) throws Exception {
        return minioClient.statObject(StatObjectArgs.builder().bucket(bucket).object(objectName).build()).toString();
    }


    /**
     * 生成一个给HTTP GET请求用的presigned URL。浏览器/移动端的客户端可以用这个URL进行下载，即使其所在的存储桶是私有的
     */
    public String getPresignedObjectUrl(String bucketName, String objectName, Integer expires) throws Exception {
        GetPresignedObjectUrlArgs build = GetPresignedObjectUrlArgs.builder().bucket(bucketName)
                .object(objectName).expiry(expires, TimeUnit.DAYS).method(Method.GET).build();
        return minioClient.getPresignedObjectUrl(build);
    }

}

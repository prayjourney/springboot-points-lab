package com.zgy.learn.bigfileupzipdown.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.learn.bigfileupzipdown.mapper.FileChunkRecordMapper;
import com.zgy.learn.bigfileupzipdown.mapper.FileRecordMapper;
import com.zgy.learn.bigfileupzipdown.pojo.FileChunkRecord;
import com.zgy.learn.bigfileupzipdown.pojo.FileRecord;
import com.zgy.learn.bigfileupzipdown.req.MultipartFileParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/10/6
 * @modified:
 */
@Service
public class FileUploadService {
    @Value("${file.upload.chunkSize}")
    private Integer CHUNK_SIZE;

    @Resource
    private FileChunkRecordMapper fileChunkRecordMapper;
    @Resource
    private FileRecordMapper fileRecordMapper;

    public String fileUpload(MultipartFileParam fileParam) {
        boolean chunkFlag = fileParam.isChunkFlag();
        return "success";
    }

    public String check(String md5) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_md5", md5);
        FileRecord record = fileRecordMapper.selectOne(queryWrapper);
        if (Objects.nonNull(record)) {
            return "文件已经上传过";
        }
        List<FileChunkRecord> records = fileChunkRecordMapper.queryByMd5(md5);
        StringBuilder result = new StringBuilder();
        records.stream().forEach(x -> result.append(x.getChunk()));
        return result.toString();
        
    }
}

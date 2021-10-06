package com.zgy.learn.bigfileupzipdown.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.learn.bigfileupzipdown.mapper.FileChunkRecordMapper;
import com.zgy.learn.bigfileupzipdown.mapper.FileRecordMapper;
import com.zgy.learn.bigfileupzipdown.pojo.FileChunkRecord;
import com.zgy.learn.bigfileupzipdown.pojo.FileRecord;
import com.zgy.learn.bigfileupzipdown.req.MultipartFileParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/10/6
 * @modified:
 */
@Service
@Slf4j
public class FileUploadService {
    @Value("${file.upload.dir}")
    private String FILE_UPLOAD_DIR;
    @Value("${file.upload.chunkSize}")
    private Integer CHUNK_SIZE;

    @Resource
    private FileChunkRecordMapper fileChunkRecordMapper;
    @Resource
    private FileRecordMapper fileRecordMapper;

    public String fileUpload(MultipartFileParam fileParam) {
        boolean chunkFlag = fileParam.isChunkFlag();
        if (!chunkFlag) {
            return singleUpload(fileParam);
        }
        return chunkUpload(fileParam);
    }

    private String singleUpload(MultipartFileParam fileParam) {
        MultipartFile file = fileParam.getFile();
        File baseFile = new File(FILE_UPLOAD_DIR);
        if (!baseFile.exists()) {
            baseFile.mkdirs();
        }
        try {
            file.transferTo(new File(baseFile, fileParam.getName()));
            Date now = new Date();
            FileRecord fileRecord = new FileRecord();
            String filePath = FILE_UPLOAD_DIR + File.separator + fileParam.getName();
            long size = FileUtil.size(new File(filePath));
            String sizeStr = size / (1024 * 1024) + "Mb";
            fileRecord.setFileName(fileParam.getName()).setFilePath(filePath).setUploadStatus(1)
                    .setFileMd5(fileParam.getMd5()).setCreateTime(now).setUpdateTime(now).setFileSize(sizeStr);
            fileRecordMapper.insert(fileRecord);
        } catch (IOException e) {
            log.error("单独上传文件错误, 问题是:{}, 时间是:{}", e.getMessage(), DateUtil.now());
        }
        return "success";
    }

    private String chunkUpload(MultipartFileParam fileParam) {
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

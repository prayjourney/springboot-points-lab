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
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.Cleaner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
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

    public String fileUpload(MultipartFileParam fileParam) throws IOException {
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
            log.error("????????????????????????, ?????????:{}, ?????????:{}", e.getMessage(), DateUtil.now());
        }
        return "success";
    }

    private String chunkUpload(MultipartFileParam fileParam) throws IOException {
        // ?????????????????????
        boolean lastFlag = false;

        int currentChunk = fileParam.getChunk();
        int totalChunk = fileParam.getTotalChunk();
        long totalSize = fileParam.getTotalSize();
        String fileName = fileParam.getName();
        String fileMd5 = fileParam.getMd5();
        MultipartFile multipartFile = fileParam.getFile();

        String parentDir = FILE_UPLOAD_DIR + File.separator + fileMd5 + File.separator;
        String tempFileName = fileName + "_tmp";

        // ?????????????????????
        File tmpFile = tmpFile(parentDir, tempFileName, multipartFile, currentChunk, totalSize, fileMd5);
        // ?????????????????????????????????
        QueryWrapper<FileChunkRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("md5", fileMd5);
        Integer count = fileChunkRecordMapper.selectCount(wrapper);
        if (count == totalChunk) {
            lastFlag = true;
        }

        if (lastFlag) {
            // ??????md5????????????
            if (!checkFileMd5(tmpFile, fileMd5)) {
                cleanUp(tmpFile, fileMd5);
                throw new RuntimeException("??????md5?????????????????????, ?????????!");
            }
            // ???????????????, ??????????????????????????????
            if (!renameFile(tmpFile, fileName)) {
                throw new RuntimeException("?????????????????????, ?????????!");
            }
            recordFile(fileName, fileMd5, parentDir);
            log.info("??????????????????, ?????????:{}, ???????????????:{}", DateUtil.now(), fileName);
        }


        return "success";
    }

    public String check(String md5) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_md5", md5);
        FileRecord record = fileRecordMapper.selectOne(queryWrapper);
        if (Objects.nonNull(record)) {
            return "?????????????????????";
        }
        List<FileChunkRecord> records = fileChunkRecordMapper.queryByMd5(md5);
        StringBuilder result = new StringBuilder();
        records.stream().forEach(x -> result.append(x.getChunk()));
        return result.toString();

    }

    private File tmpFile(String parentDir, String tempFileName, MultipartFile file,
                         int currentChunk, long totalSize, String fileMd5) throws IOException {
        log.info("??????????????????, ?????????:{}, ???????????????:{}", DateUtil.now(), tempFileName);
        long position = (currentChunk - 1) * CHUNK_SIZE;
        File tmpDir = new File(parentDir);
        File tmpFile = new File(parentDir, tempFileName);
        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }

        RandomAccessFile tempRaf = new RandomAccessFile(tmpFile, "rw");
        if (tempRaf.length() == 0) {
            tempRaf.setLength(totalSize);
        }

        // ?????????????????????
        FileChannel fc = tempRaf.getChannel();
        MappedByteBuffer map = fc.map(FileChannel.MapMode.READ_WRITE, position, file.getSize());
        map.put(file.getBytes());
        clean(map);
        fc.close();
        tempRaf.close();

        // ???????????????????????????
        FileChunkRecord fileChunkRecord = new FileChunkRecord();
        fileChunkRecord.setMd5(fileMd5).setUploadStatus(1).setChunk(currentChunk);
        fileChunkRecordMapper.insert(fileChunkRecord);
        log.info("??????????????????, ?????????:{}, ???????????????:{}", DateUtil.now(), tempFileName);
        return tmpFile;
    }

    private static void clean(MappedByteBuffer map) {
        try {
            Method getCleanerMethod = map.getClass().getMethod("cleaner");
            Cleaner.create(map, null);
            getCleanerMethod.setAccessible(true);
            Cleaner cleaner = (Cleaner) getCleanerMethod.invoke(map);
            cleaner.clean();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private boolean checkFileMd5(File file, String fileMd5) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        String checkMd5 = DigestUtils.md5DigestAsHex(fis).toUpperCase();
        fis.close();
        if (checkMd5.equals(fileMd5.toUpperCase())) {
            return true;
        }
        return false;
    }

    private void cleanUp(File file, String md5) {
        if (file.exists()) {
            file.delete();
        }
        // ??????????????????
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        fileChunkRecordMapper.delete(queryWrapper);
    }

    private boolean renameFile(File toBeRenamed, String toFileNewName) {
        // ?????????????????????????????????????????????????????????
        if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {
            log.info("File does not exist: " + toBeRenamed.getName());
            return false;
        }
        String parentPath = toBeRenamed.getParent();
        File newFile = new File(parentPath + File.separatorChar + toFileNewName);
        // ????????????, ?????????
        if (newFile.exists()) {
            newFile.delete();
        }
        return toBeRenamed.renameTo(newFile);
    }

    public void recordFile(String fileName, String fileMd5, String parentDir) {
        Date now = new Date();
        String filePath = parentDir + fileName;
        long size = FileUtil.size(new File(filePath));
        String sizeStr = size / (1024 * 1024) + "Mb";
        // ??????????????????
        FileRecord record = new FileRecord();
        record.setFileName(fileName).setFileMd5(fileMd5).setFileSize(sizeStr).setFilePath(filePath)
                .setUploadStatus(1).setCreateTime(now).setUpdateTime(now);
        fileRecordMapper.insert(record);
        // ???????????????????????????
        fileChunkRecordMapper.deleteByMd5(fileMd5);
    }

}

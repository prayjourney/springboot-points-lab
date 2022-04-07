package com.zgy.learn.bigfileupzipdown.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.learn.bigfileupzipdown.pojo.FileChunkRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/10/6
 * @modified:
 */
@Mapper
@Repository
public interface FileChunkRecordMapper extends BaseMapper<FileChunkRecord> {

    List<FileChunkRecord> queryByMd5(@Param("md5") String md5);

    @Delete("delete from file_chunk_record where md5 = #{md5}")
    int deleteByMd5(@Param("md5") String md5);

}

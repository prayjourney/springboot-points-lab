package com.zgy.learn.bigfileupzipdown.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.learn.bigfileupzipdown.pojo.FileRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/10/6
 * @modified:
 */
@Mapper
@Repository
public interface FileRecordMapper extends BaseMapper<FileRecord> {


}

package com.zgy.learn.mapstructlearn.converter;

import com.zgy.learn.mapstructlearn.pojo.Room;
import com.zgy.learn.mapstructlearn.vo.RoomVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/9/27
 * @modified:
 */
@Mapper
public interface RoomMapper {
    RoomMapper mapper = Mappers.getMapper(RoomMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "time", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            // 对象的层级映射
            @Mapping(source = "student.id", target = "studentId", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "student.name", target = "studentName", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            // 忽略这个字段, vo里面有, pojo里面没有
            @Mapping(target = "ext", ignore = true)
    })
    RoomVo building2Vo(Room room);

}

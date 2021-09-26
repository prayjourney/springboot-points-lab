package com.zgy.learn.mapstructlearn.converter;

import com.zgy.learn.mapstructlearn.pojo.Student;
import com.zgy.learn.mapstructlearn.vo.StudentVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/9/27
 * @modified:
 */
@Mapper
public interface StudentMapper {
    StudentMapper mapper = Mappers.getMapper(StudentMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "studentId"),
            @Mapping(source = "gender", target = "gender", qualifiedByName = "convertGender"),
            @Mapping(source = "birthday", target = "birthday", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "home", target = "homeLocation"),

    })
    StudentVo student2Vo(Student student);

    @Named("convertGender")
    default String convertGender(Integer gender) {
        if (gender == 0) {
            return "女";
        }
        return "男";
    }
}

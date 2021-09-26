package com.zgy.learn.mapstructlearn;

import com.zgy.learn.mapstructlearn.converter.StudentMapper;
import com.zgy.learn.mapstructlearn.pojo.Student;
import com.zgy.learn.mapstructlearn.vo.StudentVo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class MapstructLearnApplication {

    /**
     * https://mapstruct.org/
     * https://www.baeldung.com/mapstruct
     * https://www.cnblogs.com/mmzs/p/12735212.html
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(MapstructLearnApplication.class, args);
        Student student = new Student();
        student.setId(1).setName("张三").setGender(1).setBirthday(new Date()).setHome("bj");
        StudentVo vo = StudentMapper.mapper.student2Vo(student);
        System.out.println(vo);

    }

}

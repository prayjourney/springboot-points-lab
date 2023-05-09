package com.zgy.learn.mapstructlearn;

import com.zgy.learn.mapstructlearn.converter.RoomMapper;
import com.zgy.learn.mapstructlearn.converter.StudentMapper;
import com.zgy.learn.mapstructlearn.pojo.Room;
import com.zgy.learn.mapstructlearn.pojo.Student;
import com.zgy.learn.mapstructlearn.vo.RoomVo;
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
     * https://blog.csdn.net/weixin_39047791/article/details/86611706 mapstruct+lombok冲突
     * https://juejin.cn/post/6985445221425217572
     * https://blog.csdn.net/qq122516902/article/details/87259752
     * https://blog.csdn.net/wangxueqing52/article/details/87928948
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(MapstructLearnApplication.class, args);
        Student student = new Student();
        student.setId(1).setName("张三").setGender(1).setBirthday(new Date()).setHome("bj");
        StudentVo vo = StudentMapper.mapper.student2Vo(student);
        System.out.println(vo);
        System.out.println("===========================");
        Room room = new Room();
        room.setId(100).setStudent(student).setTime(new Date());
        RoomVo roomVo = RoomMapper.mapper.building2Vo(room);
        System.out.println(roomVo);
    }

}

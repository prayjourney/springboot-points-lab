package com.zgy.learn.pagination.mapper;

import com.zgy.learn.pagination.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentMapper {
    /**
     * 查询
     *
     * @param stId
     * @return
     */
    Student getById(Integer stId);

    /**
     * 统计数量
     *
     * @param student
     * @return
     */
    Integer countNum(@Param("student") Student student);

    /**
     * 无条件分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Student> pageByLimit(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    /**
     * 条件分页, 通过对象传参
     *
     * @param student
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Student> pageByLimitWithParams(@Param("student") Student student, @Param("pageNum") Integer pageNum,
                                        @Param("pageSize") Integer pageSize);

}

package com.zgy.learn.pagination.service;

import cn.hutool.json.JSONObject;
import com.zgy.learn.pagination.mapper.StudentMapper;
import com.zgy.learn.pagination.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public Student queryById(Integer stId) {
        Student student = studentMapper.getById(stId);
        return student;
    }


    public JSONObject pageByLimit(int pageNum, int pageSize) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        Integer num = studentMapper.countNum(new Student());

        // 计算偏移量
        Integer currentIndex = (pageNum - 1) * pageSize;
        List<Student> students = studentMapper.pageByLimit(currentIndex, pageSize);
        result.putOpt("code", 200);
        result.putOpt("message", "Okay");
        data.putOpt("total", num);
        data.putOpt("list", students);
        result.putOpt("data", data);
        return result;
    }

    public JSONObject pageByLimitWithParams(Student student, int pageNum, int pageSize) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        Integer num = studentMapper.countNum(student);

        // 计算偏移量
        Integer currentIndex = (pageNum - 1) * pageSize;
        List<Student> students = studentMapper.pageByLimitWithParams(student, currentIndex, pageSize);
        result.putOpt("code", 200);
        result.putOpt("message", "Okay");
        data.putOpt("total", num);
        data.putOpt("list", students);
        result.putOpt("data", data);
        return result;
    }

}
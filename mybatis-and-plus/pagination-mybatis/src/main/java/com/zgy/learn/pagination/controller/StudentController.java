package com.zgy.learn.pagination.controller;

import cn.hutool.json.JSONObject;
import com.zgy.learn.pagination.pojo.Student;
import com.zgy.learn.pagination.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 使用limit m, n的方式分页
 * m是第m页, 不是mysql需要的偏移量, 需要转换一下, currentIndex = (m -1) * n,  n是页面大小, currentIndex是当前要开始的游标, n是包含数据
 * 的容量, mysql的分页从第0页开始, 所以currentIndex计算的时候是(m -1) * n
 */
@RestController
@RequestMapping("student")
public class StudentController {
    @Resource
    private StudentService studentService;

    @GetMapping("/selectById")
    public Student selectById(Integer id) {
        return studentService.queryById(id);
    }

    @GetMapping("/pageByLimit")
    public JSONObject pageByLimit(int pageNum, int pageSize) {
        JSONObject jsonObject = studentService.pageByLimit(pageNum, pageSize);
        return jsonObject;
    }

    @GetMapping("/pageByLimitWithParams")
    public JSONObject pageByLimitWithParams(Student student, int pageNum, int pageSize) {
        JSONObject jsonObject = studentService.pageByLimitWithParams(student, pageNum, pageSize);
        return jsonObject;
    }

}
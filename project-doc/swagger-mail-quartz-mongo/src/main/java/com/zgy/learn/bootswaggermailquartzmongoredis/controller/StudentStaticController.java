package com.zgy.learn.bootswaggermailquartzmongoredis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.learn.bootswaggermailquartzmongoredis.pojo.Student;
import com.zgy.learn.bootswaggermailquartzmongoredis.util.JSONUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Api(value = "学生Controller, 使用的是静态数据", tags = "学生管理的接口，静态数据")
@Controller
@RequestMapping("staticdata")
public class StudentStaticController {
    private static List<Student> students = new ArrayList<>();
    private static List<Integer> ids = new ArrayList<>();

    static {
        students.add(new Student(1, "张三", "男", "1年级", "2班"));
        students.add(new Student(2, "李小香", "女", "2年级", "4班"));
        students.add(new Student(3, "王大锤", "男", "3年级", "4班"));
        students.add(new Student(4, "Jason", "男", "1年级", "2班"));
        students.add(new Student(5, "May", "女", "5年级", "1班"));
        students.add(new Student(6, "zou !", "女", "3年级", "3班"));
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        ids.add(5);
        ids.add(6);
    }

    @ApiOperation(value = "查询所有的学生", notes = "查询所有的学生", httpMethod = "GET")
    @ApiImplicitParam(name = "")
    @ResponseBody
    @GetMapping("allstudent")
    public String getAllStudents() throws JsonProcessingException {
        return JSONUtils.getJsonFromObject(students);

    }

    @ApiOperation(value = "按照stId查询学生的信息", notes = "按照id查询学生", httpMethod = "GET")
    @ApiImplicitParam(name = "stId", dataType = "Integer", required = true)
    @ResponseBody
    @GetMapping("getstudentbyid")
    public String getStudentById(@RequestParam("stId") Integer stId) throws JsonProcessingException {
        Student st = new Student();
        if (null == stId || stId < 0) {
            return "id is not correct!";
        } else if (ids.contains(stId)) {
            for (int i = 0; i < ids.size(); i++) {
                if (students.get(i).getStId() == stId) {
                    st = students.get(i);
                }
            }
        } else {
            return "there isn't have a student use this id!";
        }
        return JSONUtils.getJsonFromObject(st);
    }

    // 添加一个新的学生
    @ApiOperation(value = "添加一个学生", notes = "添加一个学生", httpMethod = "POST")
    @ApiImplicitParam(name = "student", dataTypeClass = Student.class, required = true)
    @PostMapping("addstudent")
    @ResponseBody
    public String addStudent(Student student) {
        if (null == student) {
            return "student info is not correct!";
        }
        if (ids.contains(student.getStId())) {
            return "student id is already exist!";
        }
        students.add(student);
        return "add the student okay!";
    }

    // 删除一个学生
    @ApiOperation(value = "删除一个学生", notes = "删除一个学生", httpMethod = "POST")
    @ApiImplicitParam(name = "stId", dataTypeClass = Integer.class, required = true)
    @PostMapping("deletestudentbyid")
    @ResponseBody
    public String deleteStudentById(@RequestParam("stId") Integer stId) throws JsonProcessingException {
        if (null == stId || stId <= 0) {
            return "id is not correct!";
        }
        if (ids.contains(stId)) {
            // 集合从0开始计算，但是我们的id从1开始计算，获取的时候，遍历集合，学生的id和集合的id无关了
            for (int i = 0; i < ids.size(); i++) {
                if (students.get(i).getStId() == stId) {
                    students.remove(students.get(i));
                    // 找到一个就跳出来
                    return JSONUtils.getJsonFromObject(students);
                }
            }
        }
        return "student is not exists!";

    }

    // 更新一个学生
    @ApiOperation(value = "更新学生信息", notes = "更新学生信息", httpMethod = "POST")
    @ApiImplicitParam(name = "student", dataTypeClass = Student.class, required = true)
    @ResponseBody
    @RequestMapping("updateStudentById")
    public String updateStudentById(Student student) throws JsonProcessingException {
        if (student.getStId() <= 0) {
            return "student info is error!";
        }
        if (null != student) {
            if (ids.contains(student.getStId())) {
                for (int i = 0; i < ids.size(); i++) {
                    if (student.getStId() == students.get(i).getStId()) {
                        students.get(i).setStName(student.getStName());
                        students.get(i).setStGender(student.getStGender());
                        students.get(i).setStGrade(student.getStGrade());
                        students.get(i).setStClass(student.getStClass());
                        return JSONUtils.getJsonFromObject(students);
                    }
                }
            } else {
                return "没有这个学生的信息！";
            }
        }
        return "student info is error!";
    }
}

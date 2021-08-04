package com.zgy.learn.bootswaggermailquartzmongoredis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.learn.bootswaggermailquartzmongoredis.pojo.Student;
import com.zgy.learn.bootswaggermailquartzmongoredis.service.MongoService;
import com.zgy.learn.bootswaggermailquartzmongoredis.service.RedisService;
import com.zgy.learn.bootswaggermailquartzmongoredis.util.JSONUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Api(value = "学生Controller", tags = "学生管理的接口")
@Controller
public class StudentController {
    private static List<Student> students = new ArrayList<>();
    private static List<Integer> ids = new ArrayList<>();
    //Logger log = LoggerFactory.getLogger(StudentController.class); // @Slf4j作用相同
    @Autowired
    MongoService mongoService;

    @Autowired
    private RedisService redisService;

    // 增删改查页面
    @GetMapping("add")
    public String add() {
        return "addstudent";
    }

    @GetMapping("delete")
    public String delete() {
        return "deletestudent";
    }

    @GetMapping("query")
    public String query() {
        return "querystudent";
    }

    @GetMapping("update")
    public String update() {
        return "updatestudent";
    }

    // 按照所有的学生
    @ApiOperation(value = "查询所有的学生", notes = "查询所有的学生", httpMethod = "GET")
    @ResponseBody
    @GetMapping("allstudent")
    public String getAllStudents() throws JsonProcessingException {
        return JSONUtils.getJsonFromObject(mongoService.queryAll());

    }

    // 按照ID查询学生
    @ApiOperation(value = "按照stId查询学生的信息", notes = "按照id查询学生", httpMethod = "GET")
    @ApiImplicitParam(name = "stId", dataType = "Integer", required = true)
    @ResponseBody
    @GetMapping("getstudentbyid")
    public String getStudentById(@RequestParam("stId") Integer stId) throws JsonProcessingException {
        if (null == stId) {
            log.error("学生信息有误！{}", stId);
            return "学生信息有误！";
        }
        /*单个key查询的过程*/
        // 先从缓存之中查
        // 1. 判断缓存之中是否有这么一个key-value对
        if (redisService.hasKey(String.valueOf(stId))) {
            log.info("从redis缓存之中查询学生的数据！{}", stId);
            Student st = (Student) redisService.get(String.valueOf(stId));
            return JSONUtils.getJsonFromObject(st);
        } else {
            // 2.如果没有，再从数据库之中查
            log.info("====>从mongo数据库之中查找学生的数据！{}", stId);
            return JSONUtils.getJsonFromObject(mongoService.queryById(stId));
        }
    }

    // 添加一个新的学生
    @ApiOperation(value = "添加一个学生", notes = "添加一个学生", httpMethod = "POST")
    @ApiImplicitParam(name = "student", dataTypeClass = Student.class, required = true)
    @PostMapping("addstudent")
    @ResponseBody
    public String addStudent(Student student) throws JsonProcessingException {
        if (null == student || null == student.getStId()) {
            return "学生信息有误！";
        } else {
            List<Student> list = mongoService.queryAll();
            if (list.size() <= 0) {
                log.error("没有学生信息！");
                return "没有学生信息";
            } else {
                List<Integer> stIds = new ArrayList<>();
                for (Student st : list) {
                    stIds.add(st.getStId());
                }
                if (stIds.contains(student.getStId())) {
                    log.warn("学生Id已经存在！{}", student.getStId());
                    return "学生Id已经存在！";
                } else {
                    // 先把数据写入到数据库
                    String str = JSONUtils.getJsonFromObject(mongoService.insert(student));
                    log.info("mongo之中成功插入了学号为 {}的学生!", student.getStId());
                    // 然后把数据写入到缓存
                    redisService.set(String.valueOf(student.getStId()), student, 1000);
                    log.info("redis之中成功插入了学号为 {}的学生!", student.getStId());
                    return str;
                }
            }
        }
    }

    // 删除一个学生
    @ApiOperation(value = "删除一个学生", notes = "删除一个学生", httpMethod = "POST")
    @ApiImplicitParam(name = "stId", dataTypeClass = Integer.class, required = true)
    @PostMapping("deletestudentbyid")
    @ResponseBody
    public String deleteStudentById(@RequestParam("stId") Integer stId) throws JsonProcessingException {
        if (null == stId) {
            log.error("学生信息有误！");
            return "学生信息有误！";
        }
        // 先从缓存之中删除，然后从数据库之中删除
        if (redisService.hasKey(String.valueOf("stId"))) {
            redisService.del(String.valueOf("stId"));
            log.info("redis之中成功删除了学号为 {}的学生!", stId);
        }
        long count = mongoService.delete(stId);
        if (count >= 1) {
            log.info("===>mongo之中成功删除了学号为 {}的学生!", stId);
            return JSONUtils.getJsonFromObject("删除成功！");
        }
        return JSONUtils.getJsonFromObject("学生不存在！");
    }

    // 更新一个学生
    @ApiOperation(value = "更新学生信息", notes = "更新学生信息", httpMethod = "POST")
    @ApiImplicitParam(name = "student", dataTypeClass = Student.class, required = true)
    @ResponseBody
    @RequestMapping("updateStudentById")
    public String updateStudentById(Student student) throws JsonProcessingException {
        if (null == student) {
            log.warn("学生信息不允许为空！");
            return "学生信息不允许为空！";
        } else if (student.getStId() == null || null == student.getStName() ||
                null == student.getStGender() || null == student.getStGrade() ||
                null == student.getStClass()) {
            log.warn("学生信息不能为空！");
            return "学生信息不能为空！";
        } else if (student.getStId() <= 0) {
            log.warn("学生信息错误！");
            return "学生信息错误！";
        } else {
            // 更新，先更新数据库【先删除缓存，然后去更新数据库】，然后更新缓存[先删除，然后插入？ 直接更新缓存？]
            // 方案===》先从缓存之中删除[如果有的话]，然后更新数据库，最后写入到缓存之中
            if (redisService.hasKey(String.valueOf(student.getStId()))) {
                redisService.del(String.valueOf(student.getStId()));
                log.info("数据将要更新】】】===redis之中成功删除了学号为 {}的学生!", student.getStId());
            }
            Long result = mongoService.update(student);
            if (result == 1L) {
                log.info("mongo之中学号为{}的学生信息更新成功!", student.getStId());
                redisService.set(String.valueOf(student.getStId()), student, 1000);
                log.info("redis之中成功更新了学号为 {}的学生!", student.getStId());
                return "更新成功";
            } else {
                return "更新出错";
            }
            // return JSONUtils.getJsonFromObject(mongoService.update(student) == 1L ? "更新成功" : "更新出错");
        }
    }
}

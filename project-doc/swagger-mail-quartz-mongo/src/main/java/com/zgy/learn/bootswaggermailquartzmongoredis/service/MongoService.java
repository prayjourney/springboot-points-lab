package com.zgy.learn.bootswaggermailquartzmongoredis.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.zgy.learn.bootswaggermailquartzmongoredis.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoService {
    @Autowired
    private MongoTemplate mongoTemplate;

    // 查询所有的学生
    public List<Student> queryAll() {
        return mongoTemplate.findAll(Student.class);
    }

    // 按照ID查询
    public Student queryById(Integer stId) {
        if (stId <= 0 || null == stId) {
            throw new RuntimeException("stId有异常！");
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("stId").is(stId));
        return mongoTemplate.findOne(query, Student.class);

    }

    // 插入学生
    public Student insert(Student student) {
        if (student.getStId() <= 0 || null == student.getStId()) {
            throw new RuntimeException("stId有异常！");
        }
        return mongoTemplate.insert(student, "student");

    }

    // 按照ID删除
    public Long delete(Integer stId) {
        if (stId <= 0 || null == stId) {
            throw new RuntimeException("stId有异常！");
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("stId").is(stId));
        DeleteResult result = mongoTemplate.remove(query, Student.class);
        if (result.getDeletedCount() >= 1) {
            return result.getDeletedCount();
        } else {
            return -1L;
        }
    }

    // 更新一个学生
    public Long update(Student student) {
        if (student.getStId() <= 0 || null == student.getStId()) {
            throw new RuntimeException("stId有异常！");
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("stId").is(student.getStId()));
        Update update = new Update();
        // 主键不是stId，而是_id
        // 每一个字段，都要去按照key来更新
        update.set("stName", student.getStName()).set("stGender", student.getStGender()).
                set("stGrade", student.getStGrade()).set("stClass", student.getStClass());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Student.class, "student");
        long modifiedCount = updateResult.getModifiedCount();
        if (modifiedCount == 1) {
            return 1L;
        } else {
            return -1L;
        }

    }


}

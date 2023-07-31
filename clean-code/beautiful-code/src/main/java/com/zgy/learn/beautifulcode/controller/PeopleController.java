package com.zgy.learn.beautifulcode.controller;

import com.zgy.learn.beautifulcode.pojo.Game;
import com.zgy.learn.beautifulcode.pojo.req.PeopleReq;
import com.zgy.learn.beautifulcode.service.PeopleService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * @author: pray-journey.io
 * @description: 1.参数校验 2.使用curl请求接口
 * @date: created in 2021/9/28 updated in 2023/6/8
 * @modified:
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/people")
public class PeopleController {
    @Resource
    private PeopleService peopleService;

    /**
     * POST无参数
     * curl -X POST http://localhost:10118/people/post/test
     * curl -X POST 'http://localhost:10118/people/post/test'
     */
    @PostMapping("/post/test")
    public String addPeopleByParam() {
        return peopleService.ok();
    }

    /**
     * POST使用query参数
     * curl -X POST http://localhost:10118/people/post/query?name=zhangsan&info=hello
     * curl -X POST 'http://localhost:10118/people/post/query?name=zhangsan&info=hello'
     * curl -X POST -d "name=zhangsan&info=hello" http://localhost:10118/people/post/query
     * curl -X POST -d "name=zhangsan&info=hello" 'http://localhost:10118/people/post/query'
     */
    @PostMapping("/post/query")
    public String addPeopleByParam(@RequestParam("name") String name, @RequestParam("info") String info) {
        log.info("name: {}, info:{} ", name, info);
        return peopleService.ok();
    }

    /**
     * POST使用JSON参数, -d参数要用单引号包裹, 字段用双引号包裹 , 必须带上-H "Content-Type: application/json"
     * 错误: curl -X POST -H "Content-Type: application/json" -d "{'name': 'zhangsan', 'type': '大乱斗', 'description':'很好玩'}" http://localhost:10118/people/post/add
     *
     * 正确: curl -X POST -H "Content-Type: application/json" -d '{"name": "zhangsan", "type": "大乱斗", "description":"很好玩"}' http://localhost:10118/people/post/add
     * 正确: curl -X POST -H "Content-Type: application/json" -d '{"name": "zhangsan", "type": "大乱斗", "description":"很好玩"}' 'http://localhost:10118/people/post/add'
     */
    @PostMapping("/post/add")
    public String addGamePost(@RequestBody @Valid Game game) {
        log.info(game.toString());
        return game.toString();
    }

    @PostMapping("/param/add")
    public String addPeopleByParam(@Valid PeopleReq peopleReq) {
        log.info(peopleReq.toString());
        return peopleService.ok();
    }

    // 对象参数的校验
    @PostMapping("/body/add")
    public String addPeopleByBody(@Validated PeopleReq peopleReq) {
        log.info(peopleReq.toString());
        return peopleService.ok();
    }

    @PostMapping("/json/add")
    public String addPeopleByJson(@RequestBody @Valid PeopleReq peopleReq) {
        log.info(peopleReq.toString());
        return peopleService.ok();
    }

    // 单个参数的校验
    @GetMapping("/single-param/validate")
    public void singleParamValidate(@PositiveOrZero Integer id,
                                    @NotNull @Length(min = 3, max = 10, message = "超标") String name,
                                    @Email(message = "不是email") String email) {
        log.info("id:{}, name:{}, email:{}", id, name, email);
    }

}

package com.zgy.learn.beautifulcode.controller;

import com.zgy.learn.beautifulcode.pojo.req.PeopleReq;
import com.zgy.learn.beautifulcode.service.PeopleService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/9/28
 * @modified:
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/people")
public class PeopleController {
    @Resource
    private PeopleService peopleService;

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

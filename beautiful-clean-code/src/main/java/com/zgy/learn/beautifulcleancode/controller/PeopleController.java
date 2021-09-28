package com.zgy.learn.beautifulcleancode.controller;

import com.zgy.learn.beautifulcleancode.pojo.req.PeopleReq;
import com.zgy.learn.beautifulcleancode.service.PeopleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/9/28
 * @modified:
 */
@RestController
@RequestMapping("/people")
public class PeopleController {
    @Resource
    private PeopleService peopleService;

    @PostMapping("jsr/add")
    public String addPeopleByJsr(PeopleReq peopleReq) {
        peopleService.addPeople(peopleReq);
        return "success";
    }
}

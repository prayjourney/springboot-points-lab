package com.zgy.learn.beautifulcleancode.service;

import com.zgy.learn.beautifulcleancode.pojo.req.PeopleReq;
import org.springframework.stereotype.Service;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021/9/28
 * @modified:
 */
@Service
public class PeopleService {
    public String addPeople(PeopleReq peopleReq) {
        return "success";
    }
}

package com.zgy.learn.pagination.controller;

import com.github.pagehelper.PageInfo;
import com.zgy.learn.pagination.pojo.City;
import com.zgy.learn.pagination.service.CityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-04-03
 * @modified:
 */
@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/getAll")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/getById")
    public City getById(Integer ctId) {
        return cityService.getById(ctId);
    }

    @GetMapping("/getByPage")
    public PageInfo getByPage(Integer pageNum, Integer pageSize) {
        return cityService.getByPage(pageNum, pageSize);
    }

    @GetMapping("/getByPageWithParams")
    public PageInfo getByPageWithParams(Integer ctId, String ctName, String ctProvince, Integer pageNum, Integer pageSize) {
        if (null == ctId || "".equals(ctId)) {
            ctId = null;
        }
        if (StringUtils.isBlank(ctName)) {
            ctName = null;
        }
        if (StringUtils.isBlank(ctProvince)) {
            ctProvince = null;
        }
        return cityService.getByPageWithParams(ctId, ctName, ctProvince, pageNum, pageSize);
    }

    @GetMapping("/getByPageWithEntity")
    public PageInfo getByPageWithEntity(City city, Integer pageNum, Integer pageSize) {
        return cityService.getByPageWithEntity(city, pageNum, pageSize);
    }

}

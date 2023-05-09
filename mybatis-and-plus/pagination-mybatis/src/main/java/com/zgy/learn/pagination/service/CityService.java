package com.zgy.learn.pagination.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgy.learn.pagination.mapper.CityMapper;
import com.zgy.learn.pagination.pojo.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-04-03
 * @modified:
 */
@Service
public class CityService {
    @Autowired
    private CityMapper cityMapper;

    public City getById(Integer ctId) {
        return cityMapper.getById(ctId);
    }

    public List<City> getAllCities() {
        return cityMapper.getAllCities();
    }

    /**
     * 分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo getByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<City> cityList = cityMapper.getByPageNumSize(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(cityList);
        return pageInfo;
    }

    /**
     * 条件分页
     *
     * @param ctId
     * @param ctName
     * @param ctProvince
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo getByPageWithParams(Integer ctId, String ctName, String ctProvince, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<City> cityList = cityMapper.getByConditionPageNumSize(ctId, ctName, ctProvince, pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(cityList);
        return pageInfo;
    }

    /**
     * 条件分页
     *
     * @param city
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo getByPageWithEntity(City city, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<City> cityList = cityMapper.getByObjectConditionPageNumSize(city, pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(cityList);
        return pageInfo;
    }

}

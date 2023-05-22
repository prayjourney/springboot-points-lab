package com.zgy.learn.securityjwttoken.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgy.learn.securityjwttoken.pojo.JwtUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JwtUserMapper extends BaseMapper<JwtUser> {
    /**
     * 通过ID查询
     */
    JwtUser queryById(Integer id);

    /**
     * 通过username查询
     */
    JwtUser queryByName(String username);

    /**
     * 统计总行数
     */
    long count(JwtUser jwtUser);

    /**
     * 批量新增数据
     */
    int insertBatch(@Param("entities") List<JwtUser> entities);

    /**
     * 批量新增或按主键更新数据
     */
    int insertOrUpdateBatch(@Param("entities") List<JwtUser> entities);

    /**
     * 修改数据
     */
    int update(JwtUser jwtUser);

    /**
     * 通过主键删除数据
     */
    int deleteById(Integer id);

}


package com.zgy.learn.securityjwttoken.mapper;

import com.zgy.learn.securityjwttoken.pojo.JwtUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JwtUserMapper {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    JwtUser queryById(Integer id);

    /**
     * 统计总行数
     *
     * @param jwtUser 查询条件
     * @return 总行数
     */
    long count(JwtUser jwtUser);

    /**
     * 新增数据
     *
     * @param jwtUser 实例对象
     * @return 影响行数
     */
    int insert(JwtUser jwtUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<JwtUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<JwtUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<JwtUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<JwtUser> entities);

    /**
     * 修改数据
     *
     * @param jwtUser 实例对象
     * @return 影响行数
     */
    int update(JwtUser jwtUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}


package com.zgy.learn.securityjwttoken.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class JwtUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 角色
     */
    private String roles;
    /**
     * 权限
     */
    private String authorities;
    /**
     * ip地址
     */
    private String ip;
    /**
     * 域名
     */
    private String domain;
    /**
     * 账户可用, 0-禁用, 1-可用
     */
    private Integer enabled;
    /**
     * 首次登录, 0-首次, 1-非首次
     */
    private Integer firstLogin;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}


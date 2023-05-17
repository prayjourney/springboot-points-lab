package com.zgy.learn.securityjwttoken.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.learn.securityjwttoken.mapper.JwtUserMapper;
import com.zgy.learn.securityjwttoken.pojo.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private JwtUserMapper jwtUserMapper;

    /**
     * 使用存储在数据库之中的信息, 构造UserDetails, 登录的时候可以进行密码对比确定是否可以成功登录
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("正在执行登录服务...");
        // 使用真实的数据库, 获取用户的信息
        QueryWrapper<JwtUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        JwtUser jwtUser = jwtUserMapper.selectOne(queryWrapper);
        if (null == jwtUser) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        // 存储在数据库之中的密码
        String passwordInDB = jwtUser.getPassword();
        // 获取用户权限
        String authorities = jwtUser.getAuthorities();
        // 获取用户角色(直接已经设置为ROLE_XXX这种格式了)
        // String roles = securityUser.getRoles();
        // String allAuthorityInfos = authorities + "," + roles;
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

        return new User(username, passwordInDB, jwtUser.getEnabled() == 0 ? false : true, false,
                false, false, grantedAuthorities);
    }

}

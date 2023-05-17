package com.zgy.learn.securityjwttoken.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.learn.securityjwttoken.mapper.JwtUserMapper;
import com.zgy.learn.securityjwttoken.pojo.JwtUser;
import com.zgy.learn.securityjwttoken.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class JwtUserService{
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationService authenticationService;

    /**
     * 登录
     */
    public Map login(String username, String password) {
        Map map = null;
        UserDetails user = authenticationService.loadUserByUsername(username);
        if (!passwordEncoder.matches(/*原始密码*/password,/*加密过的密码*/user.getPassword())) {
            map = new HashMap();
            map.put("code", 303);
            map.put("msg", "密码错误");
        }
        List<String> authorites = new ArrayList<>(user.getAuthorities().size());
        for (GrantedAuthority authorite : user.getAuthorities()) {
            authorites.add(authorite.getAuthority());
        }
        // 生成token
        map = jwtTokenUtil.getToken(username, authorites);
        map.put("code", 200);
        return map;
    }

}

package com.zgy.learn.securityjwttoken.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.learn.securityjwttoken.mapper.JwtUserMapper;
import com.zgy.learn.securityjwttoken.pojo.JwtUser;
import com.zgy.learn.securityjwttoken.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Service
public class JwtUserService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private JwtUserMapper jwtUserMapper;

    /**
     * 登录
     */
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
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

    public Map<String, Object> checkUser(String username) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<JwtUser> queryWrapper = new QueryWrapper(JwtUser.class);
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUsername(username);
        queryWrapper.setEntity(jwtUser);
        JwtUser user = jwtUserMapper.selectOne(queryWrapper);
        if (Objects.isNull(user)) {
            map.put("code", 400);
            map.put("msg", "用户不存在");
            return map;
        }
        map.put("code", 200);
        map.put("msg", "用户存在");
        return map;
    }

    public Map<String, Object> changePassword(HttpServletRequest request, String newPassword, String oldPassword) {
        Map<String, Object> map = new HashMap<>();
        String token = request.getHeader("token");
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(token);
        UserDetails user = authenticationService.loadUserByUsername(usernameFromToken);
        if (!passwordEncoder.matches(/*原始密码*/oldPassword,/*加密过的密码*/user.getPassword())) {
            map = new HashMap();
            map.put("code", 303);
            map.put("msg", "密码错误");
            return map;
        }
        String newPasswordByEncoder = passwordEncoder.encode(newPassword);
        JwtUser jwtUser = jwtUserMapper.queryByName(usernameFromToken);
        jwtUser.setPassword(newPasswordByEncoder);
        jwtUserMapper.updateById(jwtUser);
        map = new HashMap();
        map.put("code", 200);
        map.put("msg", "密码修改成功!");
        return map;
    }

}

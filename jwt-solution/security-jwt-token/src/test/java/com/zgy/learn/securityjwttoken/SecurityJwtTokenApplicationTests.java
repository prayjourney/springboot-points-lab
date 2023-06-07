package com.zgy.learn.securityjwttoken;

import com.zgy.learn.securityjwttoken.mapper.JwtUserMapper;
import com.zgy.learn.securityjwttoken.pojo.JwtUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootTest
class SecurityJwtTokenApplicationTests {
    @Autowired
    private JwtUserMapper jwtUserMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testMapper() {
        long count = jwtUserMapper.count(new JwtUser());
        System.out.println(count);
    }

    @Test
    public void testInsertUser01() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        JwtUser jwtUser = new JwtUser();
        String pwd = passwordEncoder.encode("123456");
        jwtUser.setUsername("admin").setEnabled(1).setFirstLogin(0).setAuthorities("default").setCreateTime(new Date())
                .setUpdateTime(new Date()).setPassword(pwd);
        jwtUserMapper.insert(jwtUser);
    }

    @Test
    public void testInsertUser02() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        JwtUser jwtUser = new JwtUser();
        String pwd = passwordEncoder.encode("123456");
        jwtUser.setUsername("zhangsan").setEnabled(1).setFirstLogin(0).setAuthorities("default").setCreateTime(new Date())
                .setUpdateTime(new Date()).setPassword(pwd);
        jwtUserMapper.insert(jwtUser);
    }

    @Test
    public void testInsertUser03() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        JwtUser jwtUser = new JwtUser();
        String pwd = passwordEncoder.encode("123456");
        jwtUser.setUsername("root").setEnabled(1).setFirstLogin(0).setAuthorities("default").setCreateTime(new Date())
                .setUpdateTime(new Date()).setPassword(pwd);
        jwtUserMapper.insert(jwtUser);
    }

    @Test
    public void testInsertUser04() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        JwtUser jwtUser = new JwtUser();
        String pwd = passwordEncoder.encode("123456");
        jwtUser.setUsername("xx").setEnabled(1).setFirstLogin(0).setAuthorities("").setCreateTime(new Date())
                .setUpdateTime(new Date()).setPassword(pwd);
        jwtUserMapper.insert(jwtUser);
    }

    @Test
    public void testInsertUser05() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        JwtUser jwtUser = new JwtUser();
        String pwd = passwordEncoder.encode("123456");
        jwtUser.setUsername("mc").setEnabled(0).setFirstLogin(0).setAuthorities("").setCreateTime(new Date())
                .setUpdateTime(new Date()).setPassword(pwd);
        jwtUserMapper.insert(jwtUser);
    }

}

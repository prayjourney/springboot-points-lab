package com.zgy.learn.securityjwttoken;

import com.zgy.learn.securityjwttoken.mapper.JwtUserMapper;
import com.zgy.learn.securityjwttoken.pojo.JwtUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}

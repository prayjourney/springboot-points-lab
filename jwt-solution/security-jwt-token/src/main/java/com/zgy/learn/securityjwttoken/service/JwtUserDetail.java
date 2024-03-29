package com.zgy.learn.securityjwttoken.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 从token中获取用户权限信息
 */
public class JwtUserDetail implements UserDetails {
    private String username;
    private Collection<SimpleGrantedAuthority> authorities;

    public JwtUserDetail(Claims claims) {
        this.username = claims.getSubject();
        //重token中获取权限信息
        List<String> roleAuthorizes = claims.get("roleAuthorizes", List.class);
        //权限和角色都在roleAuthorizes中,和shiro不同,shiro是权限和角色分开的
        authorities = new ArrayList<>(roleAuthorizes.size());
        roleAuthorizes.forEach((roleAuthorize) -> {
            authorities.add(new SimpleGrantedAuthority(roleAuthorize));
        });
    }

    /**
     * 获取权限
     */
    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 账号是否可用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * 账号未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}

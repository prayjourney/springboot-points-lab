package com.zgy.learn.securityjwttoken.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Data
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtTokenUtil {
    private String secret;
    private long expire;
    private String tokenHeader;

    /**
     * 使用用户名作为身份信息生成Token
     *
     * @param identityId 用户身份标识
     * @param identityId 用户的角色权限信息
     */
    public Map getToken(String identityId, List<String> Authorizes) {
        Date nowDate = new Date();
        // token过期时间
        long expireAt = nowDate.getTime() + expire * 1000;
        Date expireDate = new Date(expireAt);
        Map map = new HashMap<>();
        map.put("expireAt", expireAt);
        String token = Jwts.builder()
                // 放入唯一标识,可以是用户名或者Id
                .setSubject(identityId)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                // 自定义属性 放入用户拥有角色和权限
                .claim("roleAuthorizes", Authorizes)
                .compact();
        map.put("token", token);
        return map;
    }

    /**
     * 根据token获取身份信息
     */
    public Claims getTokenClaim(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断token是否失效
     */
    public boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 根据token获取username
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * 根据token获取失效时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getTokenClaim(token);
        return claimsResolver.apply(claims);
    }

}

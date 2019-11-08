package com.withyou.auth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author admin
 * @Date 2019-11-04 15:07
 **/
@Component
public class JwtUtil {

    private String tokenSecret = "123456";

    public String createToken(String user) {
        return Jwts.builder()
                .setId(user)
                .setExpiration(DateTime.now().plusHours(2).toDate())
                .signWith(SignatureAlgorithm.HS256, tokenSecret.getBytes())
                .compact();
    }

    public String parseToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(tokenSecret.getBytes()).parseClaimsJws(token).getBody();
        return claims.getId();
    }

    public String createToken(String user, List<String> roles) {
        String roleStr;
        if (null != roles && roles.size() > 0) {
            roleStr = String.join(",", roles);
        } else {
            roleStr = "";
        }
        Map<String, Object> claims = new HashMap<>(1);
        claims.put("roles", roleStr);
        return Jwts.builder()
                .setId(user)
                .setClaims(claims)
                .setExpiration(DateTime.now().plusHours(2).toDate())
                .signWith(SignatureAlgorithm.HS256, tokenSecret.getBytes())
                .compact();
    }
}

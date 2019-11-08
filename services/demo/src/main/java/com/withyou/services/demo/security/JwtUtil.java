package com.withyou.services.demo.security;

import com.withyou.services.demo.domain.AuthUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

/**
 * @Author admin
 * @Date 2019-11-04 15:07
 **/
@Component
public class JwtUtil {

    private String tokenSecret = "123456";

    public AuthUser parseUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(tokenSecret.getBytes()).parseClaimsJws(token).getBody();
        String roles = claims.get("roles", String.class);
        String user = claims.getId();
        return new AuthUser(user, roles);
    }
}

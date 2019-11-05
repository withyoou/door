package com.withyou.auth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;


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
        Claims claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
        return claims.getId();
    }
}

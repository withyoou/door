package com.withyou.auth.security;

import com.withyou.auth.security.domain.AuthUser;
import com.withyou.auth.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author admin
 * @Date 2019-10-08 14:21
 **/
@Component
public class PreAuthProvider implements AuthenticationManager {

    private Logger log = LoggerFactory.getLogger(PreAuthProvider.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("pre auth provider ...");
        try {
            String token = (String) authentication.getPrincipal();
            String id = jwtUtil.parseToken(token);
            List<GrantedAuthority> roles = new ArrayList<>();
            return new PreAuthenticatedAuthenticationToken(new AuthUser(id, "ROLE_A,ROLE_B"), null, roles);
        } catch (ExpiredJwtException e) {
            log.debug("Token expired. ", e);
            throw new CredentialsExpiredException("Token expired. ", e);
        } catch (Exception e) {
            log.error("Invalid token given, user not authenticated. ", e);
            throw new BadCredentialsException("Invalid token given, user not authenticated. ", e);
        }
    }
}

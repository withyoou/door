package com.withyou.auth.security;

import com.withyou.auth.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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
        String token = (String) authentication.getPrincipal();
        String id = jwtUtil.parseToken(token);
        List<GrantedAuthority> roles = new ArrayList<>();
        return new PreAuthenticatedAuthenticationToken(id, null, roles);
    }
}

package com.withyou.services.demo.security;

import com.withyou.services.demo.domain.AuthUser;
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
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

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
            String  token = (String) authentication.getPrincipal();
            AuthUser authUser = jwtUtil.parseUser(token);
            List<GrantedAuthority> roles = AuthorityUtils.commaSeparatedStringToAuthorityList(authUser.getRoles());
            return new PreAuthenticatedAuthenticationToken(authUser.getUser(), token, roles);
        } catch (ExpiredJwtException e) {
            log.error("Token expired.", e);
            throw new CredentialsExpiredException("Token expired. ", e);
        } catch (Exception e) {
            log.error("Invalid token given, user not authenticated. ", e);
            throw new BadCredentialsException("Invalid token given, user not authenticated. ", e);
        }
    }
}

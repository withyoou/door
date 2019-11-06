package com.withyou.services.demo.security;

import com.withyou.services.demo.domain.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("pre auth provider ...");
        try {
            AuthUser authUser = (AuthUser) authentication.getPrincipal();
            List<GrantedAuthority> roles = AuthorityUtils.commaSeparatedStringToAuthorityList(authUser.getRoles());
            return new PreAuthenticatedAuthenticationToken(authUser.getUser(), null, roles);
        } catch (Exception e) {
            log.error("Invalid token given, user not authenticated. ", e);
            throw new BadCredentialsException("Invalid token given, user not authenticated. ", e);
        }
    }
}

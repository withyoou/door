package com.withyou.services.demo.security;

import com.withyou.services.demo.domain.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @Author admin
 * @Date 2019-11-05 13:53
 **/
@Component
public class PreAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    private Logger log = LoggerFactory.getLogger(PreAuthFilter.class);

    @Autowired
    public PreAuthFilter(PreAuthProvider preAuthProvider) {
        setAuthenticationManager(preAuthProvider);
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
        log.info("pre auth ... {}", httpServletRequest.getServletPath());
        String roles = httpServletRequest.getHeader("roles");
        String user = httpServletRequest.getHeader("user");
        log.info("pre auth ... user: {}, role: {}", user, roles);
        if (StringUtils.isEmpty(roles) || StringUtils.isEmpty(user)) {
            return null;
        }
        return new AuthUser(user, roles);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
        return null;
    }
}

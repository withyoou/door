package com.withyou.services.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

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
        return httpServletRequest.getHeader("token");
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
        return null;
    }
}

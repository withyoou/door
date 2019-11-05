package com.withyou.auth.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author admin
 * @Date 2019-10-08 13:53
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
        log.info("pre auth ...");
        String headerToken = httpServletRequest.getHeader("token");
        if (StringUtils.isEmpty(headerToken)) {
            Cookie[] cookies = httpServletRequest.getCookies();
            if (null == cookies) {
                return null;
            }
            return Arrays.stream(httpServletRequest.getCookies())
                    .filter(c -> "token".equals(c.getName()))
                    .map(Cookie::getValue)
                    .findFirst().orElse(null);
        }
        return headerToken;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
        return null;
    }
}

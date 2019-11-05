package com.withyou.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.withyou.auth.security.domain.LoginForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * @Author admin
 * @Date 2019-10-08 15:56
 **/
@Component
public class AuthFilter extends AbstractAuthenticationProcessingFilter {

    private Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Autowired
    protected AuthFilter(AuthProvider authProvider) {
        super("/auth/login");
        setAuthenticationManager(new ProviderManager(Collections.singletonList(authProvider)));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        LoginForm loginForm = mapper.readValue(httpServletRequest.getInputStream(), LoginForm.class);
        if (StringUtils.isEmpty(loginForm.getUsername()) || StringUtils.isEmpty(loginForm.getPassword())) {
            throw new BadCredentialsException("Username \\ password could not be empty.");
        }
        log.info("auth filter... user: {}", loginForm.getUsername());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
        return getAuthenticationManager().authenticate(token);
    }
}

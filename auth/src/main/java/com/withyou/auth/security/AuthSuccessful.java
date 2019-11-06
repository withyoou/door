package com.withyou.auth.security;

import com.withyou.auth.security.domain.AuthUser;
import com.withyou.auth.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthSuccessful implements AuthenticationSuccessHandler {

    private Logger log = LoggerFactory.getLogger(AuthSuccessful.class);

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        AuthUser user = (AuthUser) token.getPrincipal();
        log.info("login success. user: {}", user);
        String jwtToken = jwtUtil.createToken(user.getUser());
        String responseJson = "{\"token\": \"" + jwtToken + "\"}";
        response.setStatus(200);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write(responseJson);
        writer.flush();
        writer.close();
    }
}

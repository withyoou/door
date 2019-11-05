package com.withyou.auth.security;

import com.withyou.auth.util.JwtUtil;
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
public class LoginSuccessful implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String user = (String) token.getCredentials();
        String jwtToken = jwtUtil.createToken(user);
        String responseJson = "{\"token\": \"" + jwtToken + "\"}";
        response.setStatus(200);
        PrintWriter writer = response.getWriter();
        writer.write(responseJson);
        writer.flush();
        writer.close();
    }
}

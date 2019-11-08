package com.withyou.auth.demo;

import com.withyou.auth.security.domain.AuthUser;
import com.withyou.auth.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author jeremy.zhao
 */
@RestController
@RequestMapping("/auth")
public class DemoRoute {

    private Logger log = LoggerFactory.getLogger(DemoRoute.class);
    @Autowired
    private JwtUtil jwtUtil;

    @Secured({"ROLE_A"})
    @GetMapping("/demo")
    public String demo() {
        return "hello";
    }

    @GetMapping("/allow")
    public String permit(@RequestParam("token") String token) {
        return jwtUtil.parseToken(token);
    }

    @GetMapping("/user")
    public AuthUser getAuthUser() {
        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authUser;
    }

    @GetMapping("/token")
    public String getInnerToken() {
        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwtUtil.createToken(authUser.getUser(), Arrays.asList(authUser.getRoles().split(",")));
    }

    @GetMapping("/roles")
    public String getRoles() {
        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authUser.getRoles();
    }
}

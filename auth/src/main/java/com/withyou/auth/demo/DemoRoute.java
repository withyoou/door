package com.withyou.auth.demo;

import com.withyou.auth.security.domain.LoginForm;
import com.withyou.auth.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class DemoRoute {

    private Logger log = LoggerFactory.getLogger(DemoRoute.class);
    @Autowired
    private JwtUtil jwtUtil;

//    @PostMapping("/login")
//    public String login(@RequestBody LoginForm loginForm) {
//        log.info("login ...");
//        return jwtUtil.createToken("123");
//    }

    @Secured({"ROLE_A"})
    @GetMapping("/demo")
    public String demo() {
        return "hello";
    }

    @GetMapping("/allow")
    public String permit() {
        return "hello";
    }
}

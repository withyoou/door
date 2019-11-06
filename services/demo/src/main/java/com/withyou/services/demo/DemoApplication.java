package com.withyou.services.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author admin
 * @Date 2019-10-11 13:51
 **/
@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Secured({"ROLE_B"})
    @GetMapping("/test/hello")
    public String demoRoute() {
        return "Hello";
    }
}

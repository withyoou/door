package com.withyou.services.demo;

import com.withyou.services.demo.client.AuthClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author admin
 * @Date 2019-10-11 13:51
 **/
@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.withyou.services.demo.client")
@MapperScan(basePackages = "com.withyou.services.demo.mapper")
public class DemoApplication {

    @Autowired
    private AuthClient authClient;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Secured({"ROLE_B"})
    @GetMapping("/test/hello")
    public String demoRoute() {
        return "Hello";
    }


    @GetMapping("/test/roles")
    public String allowRoute() {
        return authClient.getRoles();
    }
}

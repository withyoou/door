package com.withyou.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient("auth")
public interface AuthClient {

    @GetMapping("/auth/user")
    AuthUser getRolesByUser(@RequestHeader("token") String token);
}

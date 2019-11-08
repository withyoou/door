package com.withyou.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


/**
 * @author jeremy.zhao
 */
@FeignClient("S-AUTH")
public interface AuthClient {

    /**
     * retrieve
     * @param token
     * @return
     */
    @GetMapping("/auth/user")
    AuthUser getRolesByUser(@RequestHeader("token") String token);

    /**
     *
     * @param token
     * @return
     */
    @GetMapping("/auth/token")
    String exchangeInnerToken(@RequestHeader("token") String token);
}

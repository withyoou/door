package com.withyou.services.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author jeremy.zhao
 */
@FeignClient("S-AUTH")
public interface AuthClient {

    /**
     * get
     * @return
     */
    @GetMapping("/auth/roles")
    String getRoles();
}

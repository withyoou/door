package com.withyou.services.demo.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

/**
 * @author jeremy.zhao
 */
@Configuration
public class FeignConfig implements RequestInterceptor {

    private Logger log = LoggerFactory.getLogger(FeignConfig.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        log.info("feign interceptor token: {}", token);
        if (StringUtils.isEmpty(token)) {
            return;
        }
        requestTemplate.header("token", token);
    }
}

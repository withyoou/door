package com.withyou.gateway.filter;

import com.withyou.gateway.client.AuthClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author jeremy.zhao
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    private Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Autowired
    private AuthClient authClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (("/auth/login").equals(exchange.getRequest().getPath().toString())) {
            log.info("gateway filter return ... url: {}", exchange.getRequest().getPath());
            return chain.filter(exchange);
        }
        if (exchange.getRequest().getPath().toString().startsWith("/inner")) {
            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
            return chain.filter(exchange);
        }
        String token = "";
        HttpCookie cookie = exchange.getRequest().getCookies().toSingleValueMap().get("token");
        if (null == cookie) {
            List<String> headers = exchange.getRequest().getHeaders().get("token");
            if (null != headers && headers.size() > 0) {
               token = headers.get(0);
            }
        }
        log.info("gateway filter ... url: {}, token: {}", exchange.getRequest().getPath(), token);
        String innerToken = "";
        if (!StringUtils.isEmpty(token)) {
            innerToken = authClient.exchangeInnerToken(token);
        }
        log.info("gateway get inner token from auth : {}", innerToken);
        ServerHttpRequest request = exchange.getRequest().mutate().header("token", new String[]{innerToken}).build();
        ServerWebExchange build = exchange.mutate().request(request).build();
        return chain.filter(build);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

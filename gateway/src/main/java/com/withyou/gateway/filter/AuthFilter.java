package com.withyou.gateway.filter;

import com.withyou.gateway.client.AuthClient;
import com.withyou.gateway.client.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    private Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Autowired
    private AuthClient authClient;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (exchange.getRequest().getPath().equals("/auth/login")) {
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
        log.info("gateway filter ... token: {}", token);
        AuthUser authUser = authClient.getRolesByUser(token);
        log.info("gateway get user from auth : {}", authUser.toString());
        ServerHttpRequest request = exchange.getRequest().mutate().header("user", new String[]{authUser.getUser()}).header("roles", new String[]{authUser.getRoles()}).build();
        ServerWebExchange build = exchange.mutate().request(request).build();
        return chain.filter(build);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

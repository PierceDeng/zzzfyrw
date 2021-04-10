package com.zzzfyrw.gateway.filter;

import com.zzzfyrw.gateway.constant.FilterConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class JwtTokenFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("TokenFilter 过滤器走了一遍");



        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return FilterConstant.TOKEN_FILTER;
    }
}

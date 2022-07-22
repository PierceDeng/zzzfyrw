package com.zzzfyrw.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzzfyrw.common.cache.redisson.RedissonHandler;
import com.zzzfyrw.common.result.ZResultBuilder;
import com.zzzfyrw.gateway.constant.FilterConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author : dpz
 * @desc : 黑名单过滤
 * @date : 2022-07-22 16:07
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class BlackIpFilter  implements GlobalFilter, Ordered {

    private final RedissonHandler redissonHandler;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String ip = exchange.getRequest().getRemoteAddress().getHostString();
        Object o = redissonHandler.get(FilterConstant.BLACK_IP_TIP + ip);
        if(Objects.nonNull(o)){
            //存在黑名单中，进行拦截
            return Mono.defer(() -> {
                byte[] bytes = new byte[0];
                try {
                    bytes = new ObjectMapper().writeValueAsBytes(ZResultBuilder.fail("您目前无法访问"));
                } catch (Exception e) {
                    log.error("网关响应异常");
                    log.error(e.getMessage(),e);
                }
                ServerHttpResponse response = exchange.getResponse();
                response.getHeaders().add("Content-Type", MediaType.APPLICATION_JSON.toString());
                DataBuffer buffer = response.bufferFactory().wrap(bytes);
                return response.writeWith(Flux.just(buffer));
            });
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return FilterConstant.BLACK_IP_FILTER;
    }
}

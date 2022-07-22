package com.zzzfyrw.gateway.filter;

import com.zzzfyrw.common.cache.lettuce.LettuceHandler;
import com.zzzfyrw.gateway.constant.FilterConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author : dpz
 * @desc : 全局IP过滤
 * @date : 2022-07-22 14:45
 **/
@Component
@RequiredArgsConstructor
public class GlobalIpKeyResolver implements KeyResolver {

    private final LettuceHandler lettuceHandler;

    private String REQUEST_IP_COUNT = "REQUEST_IP_COUNT:";

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        String ip = Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostString();
        String key = REQUEST_IP_COUNT + ip;
        String blackIp = FilterConstant.BLACK_IP_TIP + ip;
        Integer o = (Integer) lettuceHandler.get(key);
        if(Objects.isNull(o)){
            //存入redis中
            lettuceHandler.set(key,1);
            lettuceHandler.setKeyExpireTime(key,5 * 60L);
        }else {
            //自增
            Long incr = lettuceHandler.incr(key);
            if(incr >= 2000){
                lettuceHandler.set(blackIp,1);
                lettuceHandler.setKeyExpireTime(blackIp,2 * 60 * 60L);
            }
        }
        return Mono.just(ip);
    }


}

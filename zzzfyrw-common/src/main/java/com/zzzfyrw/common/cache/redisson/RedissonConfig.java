package com.zzzfyrw.common.cache.redisson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzzfyrw.common.cache.RedisConfig;
import com.zzzfyrw.common.cache.config.RedisDeploy;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig extends RedisConfig {

    @Autowired
    private RedisDeploy redisDeploy;

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer();
        serverConfig.setDatabase(redisDeploy.dataBase)
                .setAddress("redis://" + redisDeploy.host + ":" + redisDeploy.port)
                .setConnectionMinimumIdleSize(redisDeploy.minIdle)
                .setConnectionPoolSize(redisDeploy.maxIdle)
                .setTimeout((int)redisDeploy.timeout);
        if(StringUtils.isNotEmpty(redisDeploy.password)){
            serverConfig.setPassword(redisDeploy.password);
        }
        ObjectMapper objectMapper = createObjectMapper();
        JsonJacksonCodec jsonJacksonCodec = new JsonJacksonCodec(objectMapper);
        config.setCodec(jsonJacksonCodec);
        return Redisson.create(config);
    }


}

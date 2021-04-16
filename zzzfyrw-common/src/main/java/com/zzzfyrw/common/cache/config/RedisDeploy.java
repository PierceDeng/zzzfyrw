package com.zzzfyrw.common.cache.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisDeploy {


    @Value("${spring.redis.database}")
    public int dataBase;
    @Value("${spring.redis.host}")
    public String host;
    @Value("${spring.redis.port}")
    public int port;
    @Value("${spring.redis.timeout}")
    public long timeout;
    @Value("${spring.redis.password}")
    public String password;
    @Value("${spring.redis.lettuce.pool.max-active:100}")
    public int maxActive;
    @Value("${spring.redis.lettuce.pool.max-idle:50}")
    public int maxIdle;
    @Value("${spring.redis.lettuce.pool.max-wait:-1}")
    public int maxWait;
    @Value("${spring.redis.lettuce.pool.min-idle:10}")
    public int minIdle;




}

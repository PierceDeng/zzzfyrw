package com.zzzfyrw.common.cache.lettuce;

import com.zzzfyrw.common.cache.RedisConfig;
import com.zzzfyrw.common.cache.config.RedisDeploy;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.EvictionPolicy;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@EnableCaching
@Configuration
public class LettuceConfig extends RedisConfig {

    @Autowired
    private RedisDeploy redisDeploy;

    @Bean
    public RedisTemplate redisTemplate(){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisDeploy.host);
        configuration.setPort(redisDeploy.port);
        if (StringUtils.isNotEmpty(redisDeploy.password)) {
            configuration.setPassword(redisDeploy.password);
        }
        configuration.setDatabase(redisDeploy.dataBase);
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig<>();
        genericObjectPoolConfig.setMaxTotal(redisDeploy.maxActive);
        genericObjectPoolConfig.setMinIdle(redisDeploy.minIdle);
        genericObjectPoolConfig.setMaxIdle(redisDeploy.maxIdle);
        genericObjectPoolConfig.setMaxWaitMillis(redisDeploy.maxWait);
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder builder =
                LettucePoolingClientConfiguration.builder();
        builder.poolConfig(genericObjectPoolConfig);
        builder.commandTimeout(Duration.ofSeconds(redisDeploy.timeout));
        LettuceConnectionFactory connectionFactory =
                new LettuceConnectionFactory(configuration, builder.build());
        connectionFactory.afterPropertiesSet();
        return createRedisTemplate(connectionFactory);
    }




}

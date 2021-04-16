package com.zzzfyrw.system.config.redis;

import com.zzzfyrw.common.constant.RedisConstant;
import com.zzzfyrw.system.message.RedisLogListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisListenerConfig {


    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter adapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //可以添加多个 messageListener
        container.addMessageListener(adapter, new PatternTopic(RedisConstant.LOG_CHANNEL));
        return container;
    }


    @Bean
    public MessageListenerAdapter adapter(RedisLogListener logListener) {
        return new MessageListenerAdapter(logListener, "onMessage");
    }









}

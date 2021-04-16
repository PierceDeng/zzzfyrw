package com.zzzfyrw.gateway.mono.child;

import com.zzzfyrw.common.cache.lettuce.LettuceHandler;
import com.zzzfyrw.common.constant.RedisConstant;
import com.zzzfyrw.common.spring.SpringUtils;
import com.zzzfyrw.gateway.mono.abs.ZFilterRunnable;
import com.zzzfyrw.gateway.translate.RequestLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


public class LogHandler extends ZFilterRunnable {



    private RequestLogVo logEntity;

    private LogHandler(){};

    public LogHandler(RequestLogVo entity){
        this.logEntity = entity;
    }

    @Override
    public void handle() {
        LettuceHandler LettuceHandler = SpringUtils.getBean(LettuceHandler.class);
        LettuceHandler.sendMessage(RedisConstant.LOG_CHANNEL,logEntity);
    }
}

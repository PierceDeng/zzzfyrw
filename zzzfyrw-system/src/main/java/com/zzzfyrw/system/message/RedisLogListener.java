package com.zzzfyrw.system.message;

import com.zzzfyrw.common.json.gson.GsonUtil;
import com.zzzfyrw.system.repository.entity.SysLogEntity;
import com.zzzfyrw.system.repository.mapper.SysLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class RedisLogListener implements MessageListener {


    @Autowired
    private SysLogMapper sysLogMapper;


    @Override
    public void onMessage(Message message, byte[] pattern) {

        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        SysLogEntity sysLogEntity = GsonUtil.fromJsonToObject(body, SysLogEntity.class);
        sysLogMapper.insert(sysLogEntity);


    }
}

package com.zzzfyrw.system.test.impl;

import com.zzzfyrw.system.test.IModelProcessor;
import com.zzzfyrw.system.test.body.ModelMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SecendModelProcessor implements IModelProcessor {


    @Override
    public boolean isSupport(String messageFlag) {
        return false;
    }

    @Override
    public ModelMessage processor() {
        return null;
    }
}

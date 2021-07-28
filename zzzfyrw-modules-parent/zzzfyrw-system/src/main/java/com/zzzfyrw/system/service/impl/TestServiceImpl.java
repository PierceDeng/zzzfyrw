package com.zzzfyrw.system.service.impl;

import com.zzzfyrw.common.annotation.anno.ZLog;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl {


    @ZLog
    public String test(){return "zlog";}

}

package com.zzzfyrw.system.controller;

import com.zzzfyrw.common.annotation.anno.ZLog;
import com.zzzfyrw.common.cache.lettuce.LettuceHandler;
import com.zzzfyrw.common.cache.redisson.RedissonHandler;
import com.zzzfyrw.common.constant.HeaderConstant;
import com.zzzfyrw.common.result.ZResult;
import com.zzzfyrw.common.result.ZResultBuilder;
import com.zzzfyrw.system.service.impl.TestServiceImpl;
import com.zzzfyrw.system.translate.vo.SystemUserVo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private LettuceHandler lettuceHandler;
    @Autowired
    private RedissonHandler redissonHandler;
    @Autowired
    private TestServiceImpl testService;

    @ZLog
    @PostMapping("/postT")
    public ZResult postT(@RequestBody SystemUserVo vo, HttpServletRequest request){
        String header = request.getHeader(HeaderConstant.REQUEST_ID);
        return ZResultBuilder.ok("ok");
    }

//    @ZLog
    @GetMapping("/getT")
    public ZResult postT(@RequestParam("item") String item, HttpServletRequest request){
        String header = request.getHeader(HeaderConstant.REQUEST_ID);
        testService.test();
        return ZResultBuilder.ok(item);
    }


}

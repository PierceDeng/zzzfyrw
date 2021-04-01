package com.zzzfyrw.system.controller;

import com.zzzfyrw.common.result.ZResult;
import com.zzzfyrw.common.result.ZResultBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class SystemUserController {

    @GetMapping("test")
    public ZResult<String> test(){
        return ZResultBuilder.ok("success");
    }

}

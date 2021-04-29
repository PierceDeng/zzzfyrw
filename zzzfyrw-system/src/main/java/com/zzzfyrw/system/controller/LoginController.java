package com.zzzfyrw.system.controller;

import com.zzzfyrw.common.result.ZResult;
import com.zzzfyrw.common.result.ZResultBuilder;
import com.zzzfyrw.system.service.ILoginService;
import com.zzzfyrw.system.translate.vo.SysLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/login")
public class LoginController {

    @Autowired
    private ILoginService loginService;


    @PostMapping("zAuth")
    public ZResult zAuth(@RequestBody @Validated SysLoginVo vo){
        return ZResultBuilder.ok(loginService.auth(vo));
    }



}

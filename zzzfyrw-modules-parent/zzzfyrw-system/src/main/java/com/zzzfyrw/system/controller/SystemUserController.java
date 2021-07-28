package com.zzzfyrw.system.controller;

import com.zzzfyrw.common.annotation.anno.ZLog;
import com.zzzfyrw.common.result.ZResult;
import com.zzzfyrw.common.result.ZResultBuilder;
import com.zzzfyrw.common.thread.ThreadLocalUtil;
import com.zzzfyrw.common.thread.session.SysUserInfo;
import com.zzzfyrw.system.service.ISystemUserService;
import com.zzzfyrw.system.translate.vo.SystemUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/system/user")
public class SystemUserController {

    @Autowired
    private ISystemUserService systemUserService;


    @ZLog
    @PostMapping("/queryListPage")
    public ZResult queryListPage(@RequestBody SystemUserVo vo){
        return ZResultBuilder.ok(systemUserService.queryListPage(vo));
    }

    @ZLog
    @PostMapping("/addUser")
    public ZResult addUser(@RequestBody SystemUserVo vo){
        return ZResultBuilder.ok(systemUserService.addOrEditUser(vo));
    }

    @ZLog
    @PostMapping("/delUser")
    public ZResult delUser(@RequestBody SystemUserVo vo){
        return ZResultBuilder.ok(systemUserService.delUser(vo));
    }




}

package com.zzzfyrw.system.controller;

import com.zzzfyrw.common.annotation.anno.ZLog;
import com.zzzfyrw.common.result.ZResult;
import com.zzzfyrw.common.result.ZResultBuilder;
import com.zzzfyrw.system.service.ISysRoleService;
import com.zzzfyrw.system.translate.vo.SysRoleIVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private ISysRoleService roleService;

    @ZLog
    @PostMapping("/queryListPage")
    public ZResult queryListPage(@RequestBody SysRoleIVo vo){
        return ZResultBuilder.ok(roleService.queryListPage(vo));
    }

    @ZLog
    @PostMapping("/addUser")
    public ZResult addUser(@RequestBody SysRoleIVo vo){
        return ZResultBuilder.ok(roleService.addOrEdit(vo));
    }

    @ZLog
    @PostMapping("/delUser")
    public ZResult delUser(@RequestBody SysRoleIVo vo){
        return ZResultBuilder.ok(roleService.del(vo));
    }







}

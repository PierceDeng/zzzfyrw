package com.zzzfyrw.zzzfyrw.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class SystemUserController {

    @GetMapping("test")
    public String test(){
        return "ok";
    }

}

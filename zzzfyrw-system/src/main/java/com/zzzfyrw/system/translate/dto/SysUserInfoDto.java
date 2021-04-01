package com.zzzfyrw.system.translate.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysUserInfoDto {


    private String name;
    private String phone;
    private String userId;
    private String sex;
    private String roles;
    private String organizes;
    private LocalDateTime createdTime;
    private Integer aliveStatus;


}

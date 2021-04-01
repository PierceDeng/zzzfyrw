package com.zzzfyrw.system.translate.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class SysRoleInfoDto {

    private String roleName;
    private String roleId;
    private String rowStatus;
    private LocalDateTime createdTime;
    private String createdUser;

}

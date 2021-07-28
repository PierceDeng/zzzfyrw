package com.zzzfyrw.system.translate.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SysLoginVo {

    @NotNull(message = "账号不能为空")
    private String userName;
    @NotNull(message = "密码不能为空")
    private String cipherText;



    private Integer type;



}

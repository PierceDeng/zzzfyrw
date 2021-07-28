package com.zzzfyrw.system.translate.dto;

import lombok.Data;

@Data
public class LoginResultDto {

    private String userName;
    private String accessToken;
    private String refreshToken;
    private Integer exprTime;

}

package com.zzzfyrw.gateway.translate;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class RequestLogVo {


    /**
     * GET/POST/DELETE/PUT
     */
    private String requestType;

    /**
     * 请求url
     */
    private String requestUrl;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 耗时  毫秒
     */
    private Long time;

    /**
     * 请求id
     */
    private String requestId;

    /**
     * 请求格式
     */
    private String contentType;

    /**
     * 请求时间
     */
    private LocalDateTime createdTime;

    /**
     * 请求用户id  可能为空
     */
    private String createdUser;

    /**
     * 用户类型 1平台 2对外API
     */
    private Integer createdType;

    /**
     * ip的数字类型
     */
    private Long ipInt;


}

package com.zzzfyrw.system.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author dpz
 * @since 2021-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_log")
public class SysLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * GET/POST/DELETE/PUT
     */
    @TableField("request_type")
    private String requestType;

    /**
     * 请求url
     */
    @TableField("request_url")
    private String requestUrl;

    /**
     * 请求参数
     */
    @TableField("request_params")
    private String requestParams;

    /**
     * 耗时  毫秒
     */
    @TableField("time")
    private Long time;

    /**
     * 请求id
     */
    @TableField("request_id")
    private String requestId;

    /**
     * 请求格式
     */
    @TableField("content_type")
    private String contentType;

    /**
     * ip的数字格式
     */
    @TableField("ip_int")
    private Long ipInt;

    /**
     * 请求时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 请求用户id  可能为空
     */
    @TableField("created_user")
    private String createdUser;

    /**
     * 用户类型 1平台 2对外API
     */
    @TableField("created_type")
    private Integer createdType;


}

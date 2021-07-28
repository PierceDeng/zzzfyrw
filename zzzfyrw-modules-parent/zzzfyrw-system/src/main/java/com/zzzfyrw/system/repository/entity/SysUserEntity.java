package com.zzzfyrw.system.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
 * @since 2021-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class SysUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     *  名字
     */
    @TableField("name")
    private String name;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 1男 2女 默认男
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 生日
     */
    @TableField("birthday")
    private LocalDate birthday;

    /**
     * 工号
     */
    @TableField("work_no")
    private String workNo;

    /**
     * 电子邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 用户类型 1平台注册/2微信/3腾讯QQ
     */
    @TableField("user_type")
    private Integer userType;

    /**
     * 1有效 0无效
     */
    @TableField("row_status")
    private Integer rowStatus;

    /**
     * 1启用 2禁用 3冻结(存在恢复时间)
     */
    @TableField("alive_status")
    private Integer aliveStatus;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @TableField("updated_time")
    private LocalDateTime updatedTime;

    /**
     * 创建人
     */
    @TableField("created_user")
    private Long createdUser;

    /**
     * 更新人
     */
    @TableField("updated_user")
    private Long updatedUser;


}

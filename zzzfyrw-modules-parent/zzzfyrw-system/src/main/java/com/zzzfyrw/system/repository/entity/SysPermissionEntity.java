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
 * @since 2021-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_permission")
public class SysPermissionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 1有效 0无效
     */
    @TableField("row_status")
    private Integer rowStatus;

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

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 类型 1主菜单 2子菜单页面 3按钮
     */
    @TableField("type")
    private Integer type;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 组件url
     */
    @TableField("component_url")
    private String componentUrl;

    /**
     * 按钮url
     */
    @TableField("btn_url")
    private String btnUrl;

    /**
     * 1展示 2隐藏  默认展示
     */
    @TableField("has_show")
    private Integer hasShow;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 权限编码
     */
    @TableField("permission_code")
    private String permissionCode;


}

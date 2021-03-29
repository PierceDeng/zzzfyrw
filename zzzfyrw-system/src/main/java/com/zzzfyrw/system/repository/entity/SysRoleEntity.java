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
@TableName("sys_role")
public class SysRoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色名称
     */
    @TableField("name")
    private String name;

    /**
     * 1有效 0无效
     */
    @TableField("row_status")
    private Integer rowStatus;

    /**
     * 1启用 2禁用
     */
    @TableField("has_open")
    private Integer hasOpen;

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
     * 描述
     */
    @TableField("description")
    private String description;


}

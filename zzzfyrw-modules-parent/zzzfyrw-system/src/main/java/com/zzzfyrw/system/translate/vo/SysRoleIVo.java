package com.zzzfyrw.system.translate.vo;

import com.zzzfyrw.common.page.PageVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleIVo extends PageVo {


    private String roleId;
    private List<String> roleIds;

    private String name;
    private Integer rowStatus;
    private List<String> permissionIds;



}

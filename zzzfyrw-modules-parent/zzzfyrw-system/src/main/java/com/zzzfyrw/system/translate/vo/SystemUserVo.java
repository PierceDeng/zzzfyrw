package com.zzzfyrw.system.translate.vo;

import com.zzzfyrw.common.page.PageVo;
import com.zzzfyrw.system.repository.entity.SysUserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class SystemUserVo extends PageVo {


    private String userId;
    private List<String> userIds;
    private String namePhone;
    private Integer aliveStatus;


    private String name;
    private String nickName;
    private String phone;
    private String email;
    private String workNo;
    private List<String> roleIds;
    private List<String> organizeIds;
    private Integer sex;
    private String avatar;




}

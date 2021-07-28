package com.zzzfyrw.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzzfyrw.system.translate.dto.SysRoleInfoDto;
import com.zzzfyrw.system.translate.dto.SysUserInfoDto;
import com.zzzfyrw.system.translate.vo.SysRoleIVo;
import com.zzzfyrw.system.translate.vo.SystemUserVo;

public interface ISysRoleService {


    /**
     * 新增编辑系统角色 根据角色id存在判断
     * @param vo
     * @return 用户的ID
     */
    Long addOrEdit(SysRoleIVo vo);

    /**
     * 逻辑删除 数组接受
     * @param vo
     * @return 返回删除结果
     */
    Boolean del(SysRoleIVo vo);

    /**
     * 分页条件查询角色
     * @param vo
     * @return 返回分页对象
     */
    IPage<SysRoleInfoDto> queryListPage(SysRoleIVo vo);


}

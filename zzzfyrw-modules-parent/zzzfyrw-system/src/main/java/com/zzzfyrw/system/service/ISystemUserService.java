package com.zzzfyrw.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzzfyrw.system.translate.dto.SysUserInfoDto;
import com.zzzfyrw.system.translate.vo.SystemUserVo;

public interface ISystemUserService {

    /**
     * 新增编辑系统用户  根据用户id存在判断
     * @param vo
     * @return 用户的ID
     */
    Long addOrEditUser(SystemUserVo vo);

    /**
     * 逻辑删除用户 数组接受
     * @param vo
     * @return 返回删除结果
     */
    Boolean delUser(SystemUserVo vo);

    /**
     * 分页条件查询用户
     * @param vo
     * @return 返回分页对象
     */
    IPage<SysUserInfoDto> queryListPage(SystemUserVo vo);

}

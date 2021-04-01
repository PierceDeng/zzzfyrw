package com.zzzfyrw.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzzfyrw.common.constant.SystemConstant;
import com.zzzfyrw.common.thread.ThreadLocalUtil;
import com.zzzfyrw.system.repository.entity.SysUserEntity;
import com.zzzfyrw.system.repository.mapper.SysUserMapper;
import com.zzzfyrw.system.service.ISystemUserService;
import com.zzzfyrw.system.translate.dto.SysUserInfoDto;
import com.zzzfyrw.system.translate.vo.SystemUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SystemUserServiceImpl implements ISystemUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public IPage<SysUserInfoDto> queryListPage(SystemUserVo vo) {
        int total;
        IPage<SysUserInfoDto> page = new Page<>(vo.getCurrentPage(),vo.getPageCount());
        Map<String,Object> params = new HashMap<>();
        total = sysUserMapper.countParams(params);
        page.setTotal(total);
        if(total == 0){
            return page;
        }
        params.put("count",null);
        List<SysUserInfoDto> list = sysUserMapper.queryListPage(params);
        page.setRecords(list);
        return page;
    }

    @Override
    public Long addOrEditUser(SystemUserVo vo) {
        SysUserEntity userEntity = sysUserMapper.selectById(vo.getUserId());
        if(userEntity == null){ // 添加用户
            userEntity = new SysUserEntity();
            setUser(vo,userEntity);
            userEntity.setCreatedUser(ThreadLocalUtil.getUserInfo().getUserId());
            userEntity.setUserType(SystemConstant.USER_TYPE_PLANT);
            sysUserMapper.insert(userEntity);
        }else { //编辑用户
            setUser(vo,userEntity);
            userEntity.setUpdatedUser(ThreadLocalUtil.getUserInfo().getUserId());
            sysUserMapper.updateById(userEntity);
        }
        Long userid = userEntity.getId();
        userEntity = null;
        return userid;
    }

    private void setUser(SystemUserVo vo,SysUserEntity userEntity){
        BeanUtils.copyProperties(vo,userEntity);
    }

    @Override
    public Boolean delUser(SystemUserVo vo) {
        int i = sysUserMapper.deleteBatchIds(vo.getUserIds());
        return i > 0;
    }
}

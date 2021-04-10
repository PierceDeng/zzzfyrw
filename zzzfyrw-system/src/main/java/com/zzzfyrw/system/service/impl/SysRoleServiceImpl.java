package com.zzzfyrw.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzzfyrw.common.thread.ThreadLocalUtil;
import com.zzzfyrw.system.repository.entity.SysRoleEntity;
import com.zzzfyrw.system.repository.mapper.SysRoleMapper;
import com.zzzfyrw.system.service.ISysRoleService;
import com.zzzfyrw.system.translate.dto.SysRoleInfoDto;
import com.zzzfyrw.system.translate.vo.SysRoleIVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public Long addOrEdit(SysRoleIVo vo) {
        SysRoleEntity sysRoleEntity = sysRoleMapper.selectById(vo.getRoleId());
        if(sysRoleEntity == null){ //
            sysRoleEntity = new SysRoleEntity();
            setRole(vo,sysRoleEntity);
            sysRoleEntity.setCreatedUser(ThreadLocalUtil.getUserInfo().getUserId());
            sysRoleMapper.insert(sysRoleEntity);
        }else {
            setRole(vo,sysRoleEntity);
            sysRoleMapper.updateById(sysRoleEntity);
        }
        return sysRoleEntity.getId();
    }

    private void setRole(SysRoleIVo vo,SysRoleEntity roleEntity){
        BeanUtils.copyProperties(vo,roleEntity);
    }

    @Override
    public Boolean del(SysRoleIVo vo) {
        int i = sysRoleMapper.deleteBatchIds(vo.getRoleIds());
        return i > 0;
    }

    @Override
    public IPage<SysRoleInfoDto> queryListPage(SysRoleIVo vo) {
        IPage<SysRoleInfoDto> page = new Page<>(vo.getCurrentPage(),vo.getPageCount());
        Map<String,Object> params = new HashMap<>();
        Long total = sysRoleMapper.countParams(params);
        if(total == null || total == 0){
            return page;
        }
        page.setTotal(total);
        params.put("count",null);
        List<SysRoleInfoDto> list = sysRoleMapper.queryListPage(params);
        page.setRecords(list);
        return page;
    }
}

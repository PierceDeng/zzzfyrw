package com.zzzfyrw.system.repository.mapper;

import com.zzzfyrw.system.repository.entity.SysRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzzfyrw.system.translate.dto.SysRoleInfoDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dpz
 * @since 2021-03-29
 */
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {

    List<SysRoleInfoDto> queryListPage(@Param("e") Map<String,Object> params);

    int countParams(@Param("e") Map<String,Object> params);

}

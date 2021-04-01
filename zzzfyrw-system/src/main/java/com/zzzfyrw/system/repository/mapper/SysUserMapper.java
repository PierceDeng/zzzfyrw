package com.zzzfyrw.system.repository.mapper;

import com.zzzfyrw.system.repository.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzzfyrw.system.translate.dto.SysUserInfoDto;
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
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    List<SysUserInfoDto> queryListPage(@Param("e") Map<String,Object> map);

    int countParams(@Param("e") Map<String,Object> map);

    List<SysUserInfoDto> queryList(@Param("e") Map<String,Object> map);
}

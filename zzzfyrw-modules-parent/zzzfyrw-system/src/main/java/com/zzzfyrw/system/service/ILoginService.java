package com.zzzfyrw.system.service;

import com.zzzfyrw.system.translate.dto.LoginResultDto;
import com.zzzfyrw.system.translate.vo.SysLoginVo;

public interface ILoginService {


    LoginResultDto auth(SysLoginVo vo);




}

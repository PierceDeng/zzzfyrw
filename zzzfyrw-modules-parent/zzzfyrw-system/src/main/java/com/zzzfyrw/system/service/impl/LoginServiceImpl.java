package com.zzzfyrw.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzzfyrw.common.cache.lettuce.LettuceHandler;
import com.zzzfyrw.common.constant.EncryptConstant;
import com.zzzfyrw.common.constant.RedisConstant;
import com.zzzfyrw.common.constant.SystemConstant;
import com.zzzfyrw.common.encrypt.IEncrypt;
import com.zzzfyrw.common.encrypt.aes.AesCipher;
import com.zzzfyrw.common.exception.ZBootException;
import com.zzzfyrw.common.json.gson.GsonUtil;
import com.zzzfyrw.common.jwt.TokenUtils;
import com.zzzfyrw.system.repository.entity.SysUserEntity;
import com.zzzfyrw.system.repository.mapper.SysUserMapper;
import com.zzzfyrw.system.service.ILoginService;
import com.zzzfyrw.system.translate.dto.LoginResultDto;
import com.zzzfyrw.system.translate.vo.SysLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private LettuceHandler<String> lettuceHandler;


    @Override
    public LoginResultDto auth(SysLoginVo vo) {
        String userName = vo.getUserName();
        String cipherText = vo.getCipherText();
        String password = "error";
        try {
            password = AesCipher.getInstance().decode(EncryptConstant.AES_KEY,cipherText);
            List<SysUserEntity> users = userMapper.selectList(new LambdaQueryWrapper<SysUserEntity>()
                    .eq(SysUserEntity::getName, userName)
                    .eq(SysUserEntity::getRowStatus,Boolean.TRUE)
                    .eq(SysUserEntity::getPassword, password));
            if(users.isEmpty()){
                throw new ZBootException("账户名和密码不正确");
            }
            SysUserEntity userEntity = users.get(0);
            if(userEntity.getAliveStatus() == SystemConstant.USER_DIS_ENABLED){
                throw new ZBootException("用户已停用");
            }else if(userEntity.getAliveStatus() == SystemConstant.USER_FREEZE){
                throw new ZBootException("用户已冻结");
            }
            Long userId = userEntity.getId();
            Map<String,Object> jwtParams = new HashMap<>();
            jwtParams.put("userId",userId);
            jwtParams.put("userName",userEntity.getName());
            String accessToken = TokenUtils.createToken(jwtParams);
            LocalDateTime refreshTime = LocalDateTime.now();
            refreshTime = refreshTime.plus(12, ChronoUnit.HOURS);
            long epochMilli = refreshTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            Date refreshTokenTime = new Date(epochMilli);
            String refreshToken = TokenUtils.createToken(jwtParams, refreshTokenTime);
            jwtParams.put("refreshToken",refreshToken);
            jwtParams.put("accessToken",accessToken);
            jwtParams.put("loginTime", LocalDateTime.now());

            String loginUserKey = RedisConstant.SYSTEM_USER_LOGIN_KEY_PC + userId;
            lettuceHandler.set(loginUserKey, GsonUtil.fromObjectToJson(jwtParams));
            lettuceHandler.setKeyExpireTime(loginUserKey,16 * 60 * 60L);

            LoginResultDto resultDto = new LoginResultDto();
            resultDto.setUserName(userEntity.getName());
            resultDto.setAccessToken(accessToken);
            resultDto.setRefreshToken(refreshToken);

            Map<String, Object> tokenPar = TokenUtils.parserToken(accessToken);
            if(tokenPar != null){
                Integer exp = (Integer)tokenPar.get("exp");
                Integer iat = (Integer)tokenPar.get("iat");
                resultDto.setExprTime(exp - iat);
            }
            return resultDto;
        } catch (ZBootException e){
            log.info(e.getMessage(),e);
            throw new ZBootException(e.getMessage());
        } catch (Exception e) {
            log.info("解密失败 ：{}",password);
            log.info(e.getMessage(),e);
            throw new ZBootException("登录失败");
        } finally {


        }

    }


}

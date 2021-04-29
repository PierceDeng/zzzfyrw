package com.zzzfyrw.system.service.impl;

import com.zzzfyrw.common.constant.EncryptConstant;
import com.zzzfyrw.common.encrypt.aes.AesCipher;
import com.zzzfyrw.system.service.ILoginService;
import com.zzzfyrw.system.translate.vo.SysLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginServiceImpl implements ILoginService {


    @Override
    public Object auth(SysLoginVo vo) {

        String cipherText = vo.getCipherText();
        String password = "";
        try {
            AesCipher aesCipher = AesCipher.getInstance();
            password = aesCipher.decode(EncryptConstant.AES_KEY,cipherText);
        } catch (Exception e) {
            e.printStackTrace();
        }




        return null;
    }




}

package com.zzzfyrw.common.encrypt.md5;

import com.zzzfyrw.common.constant.EncryptConstant;
import com.zzzfyrw.common.encrypt.ISignature;
import com.zzzfyrw.common.utils.CommonUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Signature implements ISignature {

    private static final String ALGORITHM = "MD5";

    private byte[] sign(byte[] content,String salt) {
        if(content == null){ return null; }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance(ALGORITHM);
            if(null == salt || "".equals(salt.trim())){
                salt = EncryptConstant.MD5_SALT;
            }
            md5.update(salt.getBytes(StandardCharsets.UTF_8));
            md5.update(content);
            byte[] bytes = md5.digest();
            return bytes;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String signToHexString(String content,String salt) {
        byte[] values=content.getBytes(StandardCharsets.UTF_8);
        String after= CommonUtils.byteArrayToHexString(sign(values,salt));
        return after;
    }

    @Override
    public String signToBase64(String content,String salt) {
        if(content == null) return null;
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        return new String(Base64.encodeBase64(sign(bytes,salt)));
    }

    public static void main(String[] args) {
        String a = "123456";
        Md5Signature md5Signature = new Md5Signature();
        String s = md5Signature.signToHexString(a,null);
        String s1 = md5Signature.signToBase64(a,null);
        System.out.println(s);
        System.out.println(s1);
    }
}

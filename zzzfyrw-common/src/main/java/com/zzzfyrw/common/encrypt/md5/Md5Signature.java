package com.zzzfyrw.common.encrypt.md5;

import com.zzzfyrw.common.constant.EncryptConstant;
import com.zzzfyrw.common.encrypt.ISignature;
import com.zzzfyrw.common.utils.CommonUtils;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Signature implements ISignature {

    private static final String ALGORITHM = "MD5";

    @Override
    public byte[] sign(byte[] content) {
        if(content == null){ return null; }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance(ALGORITHM);
            md5.update(EncryptConstant.MD5_SALT.getBytes(StandardCharsets.UTF_8));
            md5.update(content);
            byte[] bytes = md5.digest();
            return bytes;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String signToHexString(String content) {
        byte[] values=content.getBytes(StandardCharsets.UTF_8);
        String after= CommonUtils.byteArrayToHexString(sign(values));
        return after;
    }

    @Override
    public String signToBase64(String content) {
        if(content == null) return null;
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        return new String(Base64.encodeBase64(sign(bytes)));
    }

    public static void main(String[] args) {
        String a = "123456";
        Md5Signature md5Signature = new Md5Signature();
        String s = md5Signature.signToHexString(a);
        String s1 = md5Signature.signToBase64(a);
        System.out.println(s);
        System.out.println(s1);
    }
}

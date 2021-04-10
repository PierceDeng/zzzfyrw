package com.zzzfyrw.common.encrypt.aes;

import com.zzzfyrw.common.constant.EncryptConstant;
import com.zzzfyrw.common.encrypt.IEncrypt;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class AesCipher implements IEncrypt {

    private static final String ALGORITHM = "AES";

    @Override
    public String encode(String key, String content) throws Exception{
        byte[] values=encode(key,content.getBytes(StandardCharsets.UTF_8));
        if(values!=null){
            return new String(Base64.encodeBase64(values), StandardCharsets.UTF_8);
        }
        return null;
    }

    @Override
    public byte[] encode(String key, byte[] content) throws Exception{
        //1.构造密钥生成器，指定为AES算法,不区分大小写
        KeyGenerator keygen=KeyGenerator.getInstance(ALGORITHM);
        //2.根据ecnodeRules规则初始化密钥生成器
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key.getBytes());
        //生成一个128位的随机源,根据传入的字节数组
        keygen.init(128, random);
        //3.产生原始对称密钥
        SecretKey original_key=keygen.generateKey();
        //4.获得原始对称密钥的字节数组
        byte [] raw=original_key.getEncoded();
        //5.根据字节数组生成AES密钥
        SecretKey _key=new SecretKeySpec(raw, ALGORITHM);
        //6.根据指定算法AES自成密码器
        Cipher cipher=Cipher.getInstance(ALGORITHM);
        //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
        cipher.init(Cipher.ENCRYPT_MODE, _key);
        //8.根据密码器的初始化方式--加密：将数据加密
        byte [] byte_AES=cipher.doFinal(content);
        return byte_AES;
    }

    @Override
    public String decode(String key, String content) throws Exception {
        byte[] values=decode(key,Base64.decodeBase64(content));
        if(values!=null){
            return new String(values, StandardCharsets.UTF_8);
        }
        return null;
    }

    @Override
    public byte[] decode(String key, byte[] content) throws Exception{
        //1.构造密钥生成器，指定为AES算法,不区分大小写
        KeyGenerator keygen=KeyGenerator.getInstance(ALGORITHM);
        //2.根据ecnodeRules规则初始化密钥生成器
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key.getBytes());
        //生成一个128位的随机源,根据传入的字节数组
        keygen.init(128, random);
        //3.产生原始对称密钥
        SecretKey original_key=keygen.generateKey();
        //4.获得原始对称密钥的字节数组
        byte [] raw=original_key.getEncoded();
        //5.根据字节数组生成AES密钥
        SecretKey _key=new SecretKeySpec(raw, ALGORITHM);
        //6.根据指定算法AES自成密码器
        Cipher cipher=Cipher.getInstance(ALGORITHM);
        //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
        cipher.init(Cipher.DECRYPT_MODE, _key);
        byte [] byte_decode=cipher.doFinal(content);
        return byte_decode;
    }

    public static void main(String[] args) throws Exception {
        //字符串
        AesCipher aesCipher = new AesCipher();
        String a1 = "13123@!@#-/;";
        System.out.println(a1);
        String a2 = aesCipher.encode(EncryptConstant.AES_KEY, a1);
        System.out.println(a2);
        String a3 = aesCipher.decode(EncryptConstant.AES_KEY, a2);
        System.out.println(a3);
        //字节数组
        byte[] b = {1,3,4};
        System.out.println(b.length);
        byte[] encode = aesCipher.encode(EncryptConstant.AES_KEY, b);
        System.out.println(encode.length);
        byte[] decode = aesCipher.decode(EncryptConstant.AES_KEY, encode);
        System.out.println(decode.length);
    }


}

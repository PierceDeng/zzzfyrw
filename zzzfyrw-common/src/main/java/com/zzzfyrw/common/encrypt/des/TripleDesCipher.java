package com.zzzfyrw.common.encrypt.des;

import com.zzzfyrw.common.constant.EncryptConstant;
import com.zzzfyrw.common.encrypt.IEncrypt;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class TripleDesCipher implements IEncrypt {

    private static final String ALGORITHM = "DESede";


    @Override
    public String encode(String key, String content) throws Exception {
        byte[] contents=content.getBytes(StandardCharsets.UTF_8);
        contents=encode(key,contents);
        return new String(Base64.encodeBase64(contents));
    }

    @Override
    public String decode(String key, String content) throws Exception {
        byte[] contents=decode(key,Base64.decodeBase64(content));
        if(contents!=null){
            return new String(contents);
        }
        return null;
    }

    @Override
    public byte[] encode(String key, byte[] content) throws Exception {
        if(key==null||content==null){
            return null;
        }
        SecretKey deskey = new SecretKeySpec(build3DesKey(key), ALGORITHM);    //生成密钥
        Cipher c1 = Cipher.getInstance(ALGORITHM);    //实例化负责加密/解密的Cipher工具类
        c1.init(Cipher.ENCRYPT_MODE, deskey);    //初始化为加密模式
        return c1.doFinal(content);
    }

    @Override
    public byte[] decode(String key, byte[] content) throws Exception {
        if(key==null||content==null){
            return null;
        }
        SecretKey deskey = new SecretKeySpec(build3DesKey(key), ALGORITHM);
        Cipher c1 = Cipher.getInstance(ALGORITHM);
        c1.init(Cipher.DECRYPT_MODE, deskey);    //初始化为解密模式
        return c1.doFinal(content);
    }

    /*
     * 根据字符串生成密钥字节数组
     * @param keyStr 密钥字符串
     * @return
     * @throws UnsupportedEncodingException
     */
    private static byte[] build3DesKey(String keyStr) throws Exception {
        byte[] key = new byte[24];    //声明一个24位的字节数组，默认里面都是0
        byte[] temp = keyStr.getBytes(StandardCharsets.UTF_8);    //将字符串转成字节数组
        /*
         * 执行数组拷贝
         * System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
         */
        //如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
        //如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
        System.arraycopy(temp, 0, key, 0, Math.min(temp.length, key.length));
        return key;
    }

    public static void main(String[] args) throws Exception {

        TripleDesCipher desCipher = new TripleDesCipher();

        String a1 = "13123@!@#-/;";
        System.out.println(a1);
        String a2 = desCipher.encode(EncryptConstant.DES_KEY, a1);
        System.out.println(a2);
        String a3 = desCipher.decode(EncryptConstant.DES_KEY, a2);
        System.out.println(a3);
        //字节数组
        byte[] b = {1,3,4};
        System.out.println(b.length);
        byte[] encode = desCipher.encode(EncryptConstant.DES_KEY, b);
        System.out.println(encode.length);
        byte[] decode = desCipher.decode(EncryptConstant.DES_KEY, encode);
        System.out.println(decode.length);


    }
}

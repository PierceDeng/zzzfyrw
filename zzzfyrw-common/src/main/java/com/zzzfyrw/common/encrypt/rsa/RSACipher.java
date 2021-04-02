package com.zzzfyrw.common.encrypt.rsa;

import com.zzzfyrw.common.constant.EncryptConstant;
import com.zzzfyrw.common.encrypt.IEncrypt;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSACipher implements IEncrypt {

    private static final String ALGORITHM = "RSA";

    @Override
    public String encode(String key, String content) throws Exception {
        byte[] bytes = this.encode(key, content.getBytes(StandardCharsets.UTF_8));
        String encode = Base64.encodeBase64String(bytes);
        return encode;
    }

    @Override
    public String decode(String key, String content) throws Exception {
        byte[] inputByte = Base64.decodeBase64(content.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = this.decode(key, inputByte);
        String decode = new String(bytes);
        return decode;
    }

    @Override
    public byte[] encode(String key, byte[] content) throws Exception {
        byte[] decoded = Base64.decodeBase64(key);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(ALGORITHM).generatePublic(new X509EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    @Override
    public byte[] decode(String key, byte[] content) throws Exception {
        byte[] decoded = Base64.decodeBase64(key);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    public static void main(String[] args) throws Exception {

        RSACipher rsaCipher = new RSACipher();

        String a = "123456!@#./";
        System.out.println(a);
        String encode = rsaCipher.encode(EncryptConstant.RSA_PUBLIC_KEY, a);
        System.out.println(encode);
        String decode = rsaCipher.decode(EncryptConstant.RSA_PRIVATE_KEY, encode);
        System.out.println(decode);

        byte[] b = {1,3,4};
        System.out.println(b.length);
        byte[] c = rsaCipher.encode(EncryptConstant.RSA_PUBLIC_KEY, b);
        System.out.println(c.length);
        byte[] d = rsaCipher.decode(EncryptConstant.RSA_PRIVATE_KEY, c);
        System.out.println(d.length);
    }
}

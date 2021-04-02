package com.zzzfyrw.common.encrypt;

public interface ISignature {


    /**
     * 签名
     * @param content 内容
     * @return 返回原始bytes
     */
    byte[] sign(byte[] content);

    /**
     * 签名 转 十六进制
     * @param content 内容
     * @return 返回加签后的字符串
     */
    String signToHexString(String content);

    /**
     * 签名 转 Base64
     * @param content 内容
     * @return 返回加签后的字符串
     */
    String signToBase64(String content);

}

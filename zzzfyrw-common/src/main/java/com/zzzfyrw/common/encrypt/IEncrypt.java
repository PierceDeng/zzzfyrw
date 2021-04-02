package com.zzzfyrw.common.encrypt;

public interface IEncrypt {


    /**
     * 加密算法
     * @param key 密钥
     * @param content 需要加密的内容
     * @return 返回加密后的字符串
     * @throws Exception
     */
    String encode(String key,String content) throws Exception;

    /**
     * 解密算法
     * @param key 密钥
     * @param content 需要解密的内容
     * @return 返回原始内容
     * @throws Exception
     */
    String decode(String key,String content) throws Exception;

    /**
     * 加密算法
     * @param key 密钥
     * @param content 需要加密的字节数组
     * @return 返回加密后的字节数组
     * @throws Exception
     */
    byte[] encode(String key,byte[] content) throws Exception;

    /**
     * 解密算法
     * @param key 密钥
     * @param content 需要解密的字节数组
     * @return 返回原始字节数组
     * @throws Exception
     */
    byte[] decode(String key,byte[] content) throws Exception;




}

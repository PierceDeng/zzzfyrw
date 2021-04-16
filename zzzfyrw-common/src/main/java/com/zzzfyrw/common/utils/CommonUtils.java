package com.zzzfyrw.common.utils;

import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

public class CommonUtils {

    /**
     * byte数组转16进制字符串
     * @param bytes
     * @return string
     */
    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        String tmp;
        for (int i = 0; bytes != null && i < bytes.length; i++) {
            tmp = Integer.toHexString(bytes[i] & 0XFF);
            if (tmp.length() == 1) {
                stringBuilder.append("0");
            }
            stringBuilder.append(tmp);
        }
        return stringBuilder.toString();
    }

    /**
     * 获取uuid 32位 替换-符号
     * @param needCase 是否需要大写转换 null 为 false
     * @return string
     */
    public static String get32UUID(Boolean needCase){
        if(needCase == null) needCase = false;
        String uuid = UUID.randomUUID().toString().replaceAll("\\-","");
        if(needCase) uuid = uuid.toUpperCase();
        return uuid;
    }

    /**
     * 判断是否是上传文件
     * @param mediaType MediaType
     * @return Boolean
     */
    public static boolean isUploadFile(String mediaType) {
        if (Objects.isNull(mediaType)) {
            return false;
        }
        return mediaType.equals(MediaType.MULTIPART_FORM_DATA.toString())
                || mediaType.equals(MediaType.IMAGE_GIF.toString())
                || mediaType.equals(MediaType.IMAGE_JPEG.toString())
                || mediaType.equals(MediaType.IMAGE_PNG.toString())
                || mediaType.equals(MediaType.MULTIPART_MIXED.toString());
    }

    /**
     * 根据MediaType获取字符集，如果获取不到，则默认返回<tt>UTF_8</tt>
     * @param mediaType MediaType
     * @return Charset
     */
    public static Charset getMediaTypeCharset(MediaType mediaType) {
        if (Objects.nonNull(mediaType) && mediaType.getCharset() != null) {
            return mediaType.getCharset();
        } else {
            return StandardCharsets.UTF_8;
        }
    }


    public static void main(String[] args) {

        System.out.println(get32UUID(false));

    }

}

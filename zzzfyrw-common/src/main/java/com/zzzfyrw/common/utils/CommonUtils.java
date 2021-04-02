package com.zzzfyrw.common.utils;

public class CommonUtils {

    /**
     * byte数组转16进制字符串
     * @param bytes
     * @return
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


}

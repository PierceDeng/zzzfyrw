package com.zzzfyrw.common.jwt;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    /**
     * 生成token
     * @param params
     * @return
     */
    public static String createToken(Map<String,Object> params){
        if(params==null){
            return null;
        }
        return JwtComponent.getInstance().getJws(params);
    }

    /**
     * 自定义事件生成TOKEN
     * @param params
     * @return
     */
    public static String createToken(Map<String,Object> params, Date date){
        if(params==null){
            return null;
        }
        return JwtComponent.getInstance().getJws(params,date);
    }

    /**
     * 解析token为map
     * @param token
     * @return
     */
    public static Map<String,Object> parserToken(String token){
        if(!StringUtils.isNotEmpty(token)){
            return null;
        }
        return JwtComponent.getInstance().getJwt(token);
    }

    public static void main(String[] args) {
//        Map<String,Object> map = new HashMap<>();
//        Date date = new Date();
//
//        map.put("id","123");
//        map.put("name","hhhh");
//        String token = createToken(map,date);
//        System.out.println(token);
//        Map<String, Object> stringObjectMap = parserToken(token);
//        System.out.println(stringObjectMap);

        String tokenPar = "ZXlKaGJHY2lPaUpJVXpJMU5pSjkuZXlKdVlXMWxJam9pYUdob2FDSXNJbWxrSWpvaU1USXpJaXdpWlhod0lqb3hOakkxT0RJMU16WXhMQ0pwWVhRaU9qRTJNalU0TWpVek5qRXNJbTVpWmlJNk1UWXlOVGd5TlRNMk1YMC5ycmE0OVhUZEtUVV9WQ3ZnOEpSekZTUGltdTBsMkFXZlFYdFZLeDlwbHlj";
        Map<String, Object> stringObjectMap1 = parserToken(tokenPar);
        System.out.println(stringObjectMap1);
        Integer exp = (Integer)stringObjectMap1.get("exp");
        Date exprTime = new Date(exp * 1000L);
        Date now = new Date();
        boolean after = now.before(exprTime);
        System.out.println(exprTime);
        System.out.println(now);
        System.out.println(after);


//        LocalDateTime refreshTime = LocalDateTime.now();
//        refreshTime = refreshTime.plus(12, ChronoUnit.HOURS);
//        long toEpochMilli = refreshTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//        Date refreshToken = new Date(toEpochMilli);
//        System.out.println(refreshToken);

    }

}

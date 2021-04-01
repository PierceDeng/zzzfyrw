package com.zzzfyrw.common.jwt;

import org.apache.commons.lang3.StringUtils;

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
        Map<String,Object> map = new HashMap<>();
        map.put("id","123");
        map.put("name","hhhh");
        String token = createToken(map);
        System.out.println(token);
        Map<String, Object> stringObjectMap = parserToken(token);
        System.out.println(stringObjectMap);
    }

}

package com.zzzfyrw.common.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FastJsonUtil {

    private static SerializerFeature[] features = {
            //输出空值字段
            SerializerFeature.WriteMapNullValue,
            //如果数组结果为null,则输出为[],而不是null
            SerializerFeature.WriteNullListAsEmpty,
            //数值字段为null,则输出为0,而不是null
            SerializerFeature.WriteNullNumberAsZero,
            //Boolean字段为null,则输出为false,而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            //字符类型如果为null,则输出为" ",而不是null
            SerializerFeature.WriteNullStringAsEmpty
    };

    public static <T> T jsonToObject(String json,Class<T> tClass){
        T t = JSONObject.parseObject(json, tClass);
        return t;
    }

    public static <T> List<T> jsonToList(String str, Class<T> t){
        return JSON.parseArray(str,t);
    }

    public static Map jsonToMap(String str){
        return jsonToObject(str, LinkedHashMap.class);
    }

    public static JSONObject jsonToJsonObject(String str){
        return JSONObject.parseObject(str);
    }

    public static String toJsonStr(Object obj){
        return JSONObject.toJSONString(obj,features);
    }

}

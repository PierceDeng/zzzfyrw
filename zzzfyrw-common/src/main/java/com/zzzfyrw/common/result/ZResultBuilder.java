package com.zzzfyrw.common.result;

import com.alibaba.fastjson.JSONObject;
import com.zzzfyrw.common.enums.ResultEnum;

public class ZResultBuilder {


    public static <T> ZResult<T> ok(T t){
        ZResult<T> result = new ZResult<>();
        result.setData(t);
        result.setSuccess(Boolean.TRUE);
        result.setTimestamp(System.currentTimeMillis());
        codeMsgBuild(result,ResultEnum.SUCCESS);
        return result;
    }

    public static <T> ZResult<T> ok(T t,String msg){
        ZResult<T> result = ok(t);
        result.setMessage(msg);
        return result;
    }

    public static <T> ZResult<T> warpOk(T t,String msg){
        ZResult<T> result = new ZResult<>();
        result.setData(t);
        result.setSuccess(Boolean.TRUE);
        result.setTimestamp(System.currentTimeMillis());
        codeMsgBuild(result,ResultEnum.WARP_SUCCESS);
        result.setMessage(msg);
        return result;
    }

    public static <T> ZResult<T> fail(){
        ZResult<T> result = new ZResult<>();
        result.setSuccess(Boolean.FALSE);
        result.setTimestamp(System.currentTimeMillis());
        codeMsgBuild(result,ResultEnum.FAIL);
        return result;
    }

    public static <T> ZResult<T> fail(String msg){
        ZResult<T> fail = fail();
        fail.setMessage(msg);
        return fail;
    }

    public static <T> ZResult<T> timeout(){
        ZResult<T> result = new ZResult<>();
        result.setSuccess(Boolean.FALSE);
        result.setTimestamp(System.currentTimeMillis());
        codeMsgBuild(result,ResultEnum.TIME_OUT);
        return result;
    }

    public static <T> ZResult<T> timeout(String msg){
        ZResult<T> timeout = timeout();
        timeout.setMessage(msg);
        return timeout;
    }


    private static void codeMsgBuild(ZResult<?> result,ResultEnum resultEnum){
        if(null != result && null != resultEnum){
            result.setCode(resultEnum.getCode());
            result.setMessage(resultEnum.getMsg());
        }
    }

}

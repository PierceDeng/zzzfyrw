package com.zzzfyrw.common.exception;

import com.zzzfyrw.common.enums.ResultEnum;

public class ZBootException extends RuntimeException{

    private ResultEnum resultEnum = ResultEnum.FAIL;
    private Object result;

    public ZBootException(ResultEnum enums,String msg,Object result){
        super(msg);
        this.resultEnum = enums;
        this.result = result;
    }


    public ZBootException(String message) {
        super(message);
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }
}

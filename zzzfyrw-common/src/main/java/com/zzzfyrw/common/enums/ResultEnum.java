package com.zzzfyrw.common.enums;

public enum ResultEnum {


    SUCCESS(0,"SUCCESS"),

    TIME_OUT(500,"TIME OUT ERR"),

    FAIL(400,"FAIL REQUEST ERR")

    ;

    private int code;
    private String msg;


    ResultEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

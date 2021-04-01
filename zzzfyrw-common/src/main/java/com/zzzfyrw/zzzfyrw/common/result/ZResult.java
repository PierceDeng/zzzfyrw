package com.zzzfyrw.zzzfyrw.common.result;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
public class ZResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T data;
    private String message;
    private Integer code;
    private Boolean success;
    private long timestamp;

    protected ZResult(){};


    public Boolean isSuccess(){
        return null != this.success && this.success;
    }

}

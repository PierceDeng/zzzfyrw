package com.zzzfyrw.system.repository.exception;

import com.zzzfyrw.common.enums.ResultEnum;
import com.zzzfyrw.common.exception.ZBootException;

public class ZLoginException extends ZBootException {

    public ZLoginException(ResultEnum enums, String msg, Object result) {
        super(enums, msg, result);
    }

    public ZLoginException(String message) {
        super(message);
    }
}

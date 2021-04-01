package com.zzzfyrw.common.exception;

import com.zzzfyrw.common.result.ZResult;
import com.zzzfyrw.common.result.ZResultBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@ControllerAdvice
public class ZExceptionHandler {


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ZResult exceptionHandler(Exception e){
        return ZResultBuilder.fail();
    }



}



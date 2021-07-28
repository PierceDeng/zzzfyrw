package com.zzzfyrw.common.exception;

import com.zzzfyrw.common.enums.ResultEnum;
import com.zzzfyrw.common.result.ZResult;
import com.zzzfyrw.common.result.ZResultBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@ControllerAdvice
public class ZExceptionHandler {


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ZResult<Object> exceptionHandler(Exception e){
        log.error(e.getMessage(),e);
        return ZResultBuilder.fail();
    }

    @ResponseBody
    @ExceptionHandler(value = ZBootException.class)
    public ZResult<Object> zBootExceptionHandler(ZBootException e){
        log.error(e.getMessage());
        ResultEnum resultEnum = e.getResultEnum();
        ZResult<Object> result = null;
        switch (resultEnum){
            case SUCCESS:
                result = ZResultBuilder.ok(e.getResult(),e.getMessage());
                break;
            case WARP_SUCCESS:
                result = ZResultBuilder.warpOk(e.getResult(),e.getMessage());
                break;
            case TIME_OUT:
                result = ZResultBuilder.timeout(e.getMessage());
                break;
            case FAIL:
            default:
                result = ZResultBuilder.fail(e.getMessage());
                break;
        }
        return result;
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ZResult<Object> parameterExceptionHandler(MethodArgumentNotValidException e) {
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                return ZResultBuilder.fail(errors.get(0).getDefaultMessage());
            }
        }
        return ZResultBuilder.fail();
    }




}



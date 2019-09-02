package com.crazy.portal.config.exception;

import com.crazy.portal.bean.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lee on 2019/6/6.
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseResponse parameterExceptionHandler(Exception exception) {

        log.error("", exception);
        BaseResponse response = new BaseResponse();
        if(exception instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException paramException = (MethodArgumentNotValidException) exception;
            List<ObjectError> errorList = paramException.getBindingResult().getAllErrors();
            String msg = errorList.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
            response.fail(-1, msg);
            return response;
        }

        if(exception instanceof BusinessException){
            BusinessException ex = (BusinessException)exception;
            response.setCode(ex.getErrorCode());
            response.setMsg(ex.getMessage());
            return response;
        }

        response.systemException();
        return response;
    }

}

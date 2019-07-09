package com.crazy.portal.config.exception;

import com.crazy.portal.bean.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

/**
 * Created by lee on 2019/6/6.
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseResponse parameterExceptionHandler(Exception exception) {
        BaseResponse response = new BaseResponse();
        if(exception instanceof MethodArgumentNotValidException){
            StringBuilder stringBuilder = new StringBuilder();
            MethodArgumentNotValidException paramException = (MethodArgumentNotValidException) exception;
            List<ObjectError> errorList =  paramException.getBindingResult().getAllErrors();
            for(ObjectError error : errorList){
                stringBuilder.append(error.getDefaultMessage());
                stringBuilder.append(",");
            }
            String msg = stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "").toString();
            response.fail(-1, msg);
            return response;
        }
        if(exception instanceof IllegalArgumentException){
            response.fail(-1, exception.getMessage());
            return response;
        }
        if(exception instanceof BusinessException){
            BusinessException ex = (BusinessException)exception;
            response.setCode(ex.getErrorCode());
            response.setMsg(ex.getMessage());
            return response;
        }
        response.systemException();
        log.error(exception.getMessage(), exception);
        return response;
    }

}

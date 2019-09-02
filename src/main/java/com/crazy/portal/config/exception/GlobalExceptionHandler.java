package com.crazy.portal.config.exception;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.util.ErrorCodes.CommonEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

        if(exception instanceof MethodArgumentNotValidException){

            MethodArgumentNotValidException paramException = (MethodArgumentNotValidException) exception;
            List<ObjectError> errorList = paramException.getBindingResult().getAllErrors();

            String msg = errorList.stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(","));

            return new BaseResponse(CommonEnum.REQ_PARAM_FORMAT_ERROR.getCode(),msg);
        }

        if(exception instanceof BusinessException){
            BusinessException ex = (BusinessException)exception;
            return new BaseResponse(ex.getErrorCode(),ex.getMessage());
        }

        return new BaseResponse(CommonEnum.SYSTEM_EXCEPTION.getCode(),CommonEnum.SYSTEM_EXCEPTION.getZhMsg());
    }

}

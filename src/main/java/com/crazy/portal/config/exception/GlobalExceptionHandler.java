package com.crazy.portal.config.exception;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.util.ErrorCodes.CommonEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by lee on 2019/6/6.
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends BaseController {

    /**
     * 全局异常捕捉
     * 注意:如果需要新加指定异常处理逻辑(x instanceOf x)需要在操作日志AOP中进行抛出,不然异常栈日志将会被aop进行全局捕获
     * @see com.crazy.portal.aop.OperationAspect setErrorMsgAndThrowException
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseResponse parameterExceptionHandler(Exception exception) {

        if(exception instanceof MethodArgumentNotValidException){
            String msg = super.getValidExceptionMsg((MethodArgumentNotValidException) exception);
            return new BaseResponse(CommonEnum.REQ_PARAM_FORMAT_ERROR.getCode(),msg);
        }

        if(exception instanceof BusinessException){
            BusinessException ex = (BusinessException)exception;
            return new BaseResponse(ex.getErrorCode(),ex.getMessage());
        }

        log.error("", exception);
        return new BaseResponse(CommonEnum.SYSTEM_EXCEPTION.getCode(),CommonEnum.SYSTEM_EXCEPTION.getZhMsg());
    }

}

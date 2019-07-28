package com.crazy.portal.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.util.ErrorCodes.CommonEnum;
import com.crazy.portal.util.ErrorCodes.SystemManagerEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 19:55 2019/4/20
 * @Modified by:
 */
@Slf4j
@Component
public class AuthenticationFailHandler implements AuthenticationFailureHandler{



    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException {

        BaseResponse baseResponse;
        try(ServletOutputStream os = response.getOutputStream()){
            response.reset();
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=utf-8");
            if(e.getCause() instanceof NonceExpiredException){
                baseResponse = new BaseResponse(SystemManagerEnum.TOKEN_INVALID.getCode(),
                        SystemManagerEnum.TOKEN_INVALID.getZhMsg());
            } else if(e instanceof BadCredentialsException || e.getCause() instanceof BadCredentialsException){
                if(e.getMessage().contains("Kerberos")){
                    baseResponse = new BaseResponse(SystemManagerEnum.AD_AUTH_ERROR.getCode(),
                            SystemManagerEnum.AD_AUTH_ERROR.getZhMsg());
                }else{
                    baseResponse = new BaseResponse(SystemManagerEnum.ACCOUNT_ERROR.getCode(),
                            SystemManagerEnum.ACCOUNT_ERROR.getZhMsg());
                }
            } else if(e.getCause() instanceof LockedException){
                if(e.getMessage().equals("password expiration")){
                    baseResponse = new BaseResponse(SystemManagerEnum.PASSWORD_INVALID.getCode(),
                            SystemManagerEnum.PASSWORD_INVALID.getZhMsg());
                }else{
                    baseResponse = new BaseResponse(SystemManagerEnum.LOCKED.getCode(),
                            SystemManagerEnum.LOCKED.getZhMsg());
                }
            }else if(e instanceof InsufficientAuthenticationException){
                baseResponse = new BaseResponse(SystemManagerEnum.AUTH_ERROR.getCode(),
                        SystemManagerEnum.AUTH_ERROR.getZhMsg());
            }else {
                baseResponse = new BaseResponse(CommonEnum.SYSTEM_EXCEPTION.getCode(),
                        CommonEnum.SYSTEM_EXCEPTION.getZhMsg());
            }
            os.write(JSON.toJSONString(baseResponse).getBytes());
        }
    }
}

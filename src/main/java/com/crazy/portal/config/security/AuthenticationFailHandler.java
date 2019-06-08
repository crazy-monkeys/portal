package com.crazy.portal.config.security;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.util.ResponseCode.SystemManagerEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
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
public class AuthenticationFailHandler implements AuthenticationFailureHandler{



    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {

        ServletOutputStream os = null;
        BaseResponse baseResponse = new BaseResponse();
        try {
            response.reset();
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=utf-8");
            os = response.getOutputStream();

            if(e.getCause() instanceof UsernameNotFoundException){
                baseResponse.setCode(SystemManagerEnum.ACCOUNT_ERROR.getCode());
                baseResponse.setMsg(SystemManagerEnum.ACCOUNT_ERROR.getZhMsg());
                os.write(JSON.toJSONString(baseResponse).getBytes());
                return;
            }
            if(e.getCause() instanceof LockedException){
                baseResponse.setCode(SystemManagerEnum.LOCKED.getCode());
                baseResponse.setMsg(SystemManagerEnum.LOCKED.getZhMsg());
                os.write(JSON.toJSONString(baseResponse).getBytes());
                return;
            }
            if(e instanceof InsufficientAuthenticationException){
                baseResponse.setCode(SystemManagerEnum.AUTH_ERROR.getCode());
                baseResponse.setMsg(SystemManagerEnum.AUTH_ERROR.getZhMsg());
                os.write(JSON.toJSONString(baseResponse).getBytes());
                return;
            }
            String result = "{\"status\":\"error\",\"msg\":\""+e.getMessage()+"\"}";
            os.write(result.getBytes());
        } catch (IOException e1) {
            e1.printStackTrace();
        }finally {
            os.flush();
            os.close();
        }
    }
}

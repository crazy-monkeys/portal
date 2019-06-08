package com.crazy.portal.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.NonceExpiredException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.AccessDeniedException;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 19:55 2019/4/20
 * @Modified by:
 */
@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler{



    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {

        ServletOutputStream os = null;
        try {
            response.reset();
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=utf-8");
            os = response.getOutputStream();

            if(e.getCause() instanceof UsernameNotFoundException){
                os.write("{\"status\":\"error\",\"msg\":\"UsernameNotFound\"}".getBytes());
                return;
            }
            if(e.getCause() instanceof LockedException){
                os.write("{\"status\":\"error\",\"msg\":\"Locked\"}".getBytes());
                return;
            }
            if(e.getCause() instanceof AccessDeniedException){
                System.out.println("1");
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

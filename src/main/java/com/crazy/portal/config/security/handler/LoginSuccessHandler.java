package com.crazy.portal.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.config.security.JwtUserService;
import com.crazy.portal.entity.system.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import com.crazy.portal.util.ResponseCode.CommonEnum;
import org.springframework.stereotype.Component;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 19:27 2019/4/20
 * @Modified by:
 */
@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtUserService jwtUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        //获取当前登录用户
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        //生成token，并把token加密相关信息缓存，具体请看实现类
        String token = jwtUserService.saveUserLoginInfo(userDetails);
        response.setHeader("Authorization", token);
        response.setContentType("application/json;charset=utf-8");
        BaseResponse baseResponse = new BaseResponse();
        //获取权限资源
        baseResponse.success(new HashMap<>());
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            out.write(JSON.toJSONString(baseResponse).getBytes());
        } catch (IOException e) {
           log.error("序列化失败");
        }finally {
            out.flush();
            out.close();
        }
    }
}

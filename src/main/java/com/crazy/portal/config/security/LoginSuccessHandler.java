package com.crazy.portal.config.security;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import com.crazy.portal.util.ResponseCode.CommonEnum;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 19:27 2019/4/20
 * @Modified by:
 */
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private JwtUserService jwtUserService;

    public LoginSuccessHandler(JwtUserService jwtUserService) {
        this.jwtUserService = jwtUserService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //生成token，并把token加密相关信息缓存，具体请看实现类
        String token = jwtUserService.saveUserLoginInfo((UserDetails)authentication.getPrincipal());
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

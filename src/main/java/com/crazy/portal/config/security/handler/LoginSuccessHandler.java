package com.crazy.portal.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.config.security.JwtUserService;
import com.crazy.portal.dao.system.UserMapper;
import com.crazy.portal.entity.system.Resource;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.service.system.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 19:27 2019/4/20
 * @Modified by:
 */
@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @javax.annotation.Resource
    private JwtUserService jwtUserService;
    @javax.annotation.Resource
    private PermissionService permissionService;
    @javax.annotation.Resource
    private UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        //获取当前登录用户
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        //登录修改最后登录时间
        User user = userMapper.findByLoginName(userDetails.getUsername());
        user.setLastLoginTime(new Date());
        userMapper.updateByPrimaryKeySelective(user);
        //生成token，并把token加密相关信息缓存，具体请看实现类
        String token = jwtUserService.saveUserLoginInfo(userDetails);
        response.reset();
        response.setHeader("Authorization", token);
        response.setContentType("application/json;charset=utf-8");
        BaseResponse baseResponse = new BaseResponse();
        //获取权限资源
        OutputStream out = response.getOutputStream();
        try {
            Map<String,Object> map = new HashMap<>();
            List<Resource> permissions = permissionService.findAllPerMissionByUserId(userDetails.getUsername());
            map.put("permissions",permissionService.resourceTree(permissions));
            map.put("user",user);
            baseResponse.success(map);
            out.write(JSON.toJSONString(baseResponse).getBytes());
        } catch (Exception e) {
            log.error("",e);
            baseResponse.systemException();
            out.write(JSON.toJSONString(baseResponse).getBytes());
        }finally {
            out.flush();
            out.close();
        }

    }
}

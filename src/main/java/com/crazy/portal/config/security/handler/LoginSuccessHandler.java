package com.crazy.portal.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.config.security.JwtUserService;
import com.crazy.portal.dao.system.UserMapper;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.service.system.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    public static final String PERMISSIONS = "permissions";
    public static final String USER = "user";

    @javax.annotation.Resource
    private JwtUserService jwtUserService;

    @javax.annotation.Resource
    private PermissionService permissionService;

    @javax.annotation.Resource
    private UserMapper userMapper;

    /**
     * 获取当前登录人拥有的菜单
     * @return
     */
    public Map<String,?> getCurrentMenu(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        //获取当前登录用户
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        //登录修改最后登录时间
        User user = userMapper.findByLoginName(userDetails.getUsername());
        user.setLastLoginTime(new Date());
        userMapper.updateByPrimaryKeySelective(user);
        Map<String,Object> map = new HashMap<>();
        List<com.crazy.portal.entity.system.Resource> permissions = permissionService.findAllPerMissionByUserId(userDetails.getUsername());
        map.put(PERMISSIONS,permissionService.resourceTree(permissions));
        map.put(USER,user);
        return map;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {

        //获取当前登录用户的菜单列表
        Map<String,?> currentMenu = this.getCurrentMenu();
        //获取当前登录用户
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        //生成token，并把token加密相关信息缓存，具体请看实现类
        String token = jwtUserService.generateToken(userDetails);

        response.reset();
        response.setHeader("Authorization", token);
        response.setContentType("application/json;charset=utf-8");
        //获取权限资源
        try(OutputStream out = response.getOutputStream()){
            out.write(JSON.toJSONString(currentMenu).getBytes());
            out.flush();
        }catch (Exception e){
            log.error("The output stream handles exceptions",e);
        }
    }
}

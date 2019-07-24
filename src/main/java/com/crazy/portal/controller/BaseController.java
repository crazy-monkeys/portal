package com.crazy.portal.controller;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.config.security.JwtUser;
import com.crazy.portal.dao.system.RoleMapper;
import com.crazy.portal.entity.system.Role;
import com.crazy.portal.entity.system.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 12:52 2019/5/3
 * @Modified by:t
 */
@Component
public class BaseController {

    @Resource
    private RoleMapper roleMapper;

    protected User getCurrentUser(){
        try {
            User user = ((JwtUser) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal())
                    .getUser();

            return null == user ? mockUser() : user;
        }catch (Exception ex){
            return mockUser();
        }
    }

    protected Role getCurrentRole(){
        Authentication authentication= SecurityContextHolder
                .getContext()
                .getAuthentication();

        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        List<String> list = grantedAuthorities.stream()
                .map(x->x.getAuthority()).collect(Collectors.toList());
        String roleCode = list.get(0);
        return roleMapper.findRoleByCode(roleCode);
    }

    private User mockUser(){
        User user = new User();
        user.setId(1);
        return user;
    }

    protected BaseResponse successResult() {
        BaseResponse response = new BaseResponse();
        response.success();
        return response;
    }

    protected BaseResponse successResult(Object data) {
        BaseResponse response = new BaseResponse();
        response.success(data);
        return response;
    }

    protected BaseResponse errorResult(){
        BaseResponse resp = new BaseResponse();
        resp.fail(null);
        return resp;
    }
}

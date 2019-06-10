package com.crazy.portal.controller;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.config.security.JwtUser;
import com.crazy.portal.entity.system.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 12:52 2019/5/3
 * @Modified by:t
 */
@Component
public class BaseController {

    public User getCurrentUser(){
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

}

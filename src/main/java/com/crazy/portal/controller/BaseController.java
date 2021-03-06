package com.crazy.portal.controller;

import com.crazy.portal.config.security.JwtUser;
import com.crazy.portal.entity.system.UserDO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 12:52 2019/5/3
 * @Modified by:
 */
@Component
public class BaseController {

    public UserDO getCurrentUser(){
        return ((JwtUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUserDO();
    }
}

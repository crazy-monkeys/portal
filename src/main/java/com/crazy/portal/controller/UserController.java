package com.crazy.portal.controller;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.service.system.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: LoginUserController
 * @Author: God Man Qiu~
 * @Date: 2019/4/18 16:22
 *
 * 账号创建为 freeze 状态 不可登陆
 * 账号审批通过为init 状态 可以登陆 控制登陆到 资格申请页
 * 资格申请通过为 normal 状态 可正常使用系统
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    /**
     * 创建登陆账号
     * @param user
     */
    @RequestMapping("/register")
    public Boolean createUser(@RequestBody User user){
        try{
            userService.register(user);
        }catch (Exception e){
            log.error("账号注册异常！",e);
            return false;
        }
        return true;
    }

    @RequestMapping("/list")
    public List<User> selectAllUsers(){
        System.out.println(JSON.toJSONString(super.getCurrentUser()));
        return userService.selectAllUser();
    }

    /**
     * 审批
     * @Param status 0:init 1:normal
     */
    @RequestMapping("/approval")
    public boolean approvalUser(String id, Integer status){
        return userService.approvalUser(id,status);
    }
}

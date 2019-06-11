package com.crazy.portal.controller.system;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.service.system.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Desc:
 * @Author: bill
 * @Date:
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
    public BaseResponse createUser(@RequestBody User user){

        //todo 参数校验
        userService.register(user);
        return super.successResult();
    }

    /**
     * TODO 分页查询
     * @return
     */
    @RequestMapping("/list")
    public List<User> selectAllUsers(){
        return userService.selectAllUser();
    }
}

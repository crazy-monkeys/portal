package com.crazy.portal.controller.system;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.service.system.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


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
    @PostMapping("/register")
    public BaseResponse createUser(@RequestBody User user){
        //todo 参数校验
        userService.register(user);
        return super.successResult();
    }

    /**
     * 分页查询
     * @return
     */
    @PostMapping("/list")
    public BaseResponse selectAllUsers(@RequestBody(required = false) User user,
                                       @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                       @RequestParam(required = false,defaultValue = "10") Integer pageSize){

        return super.successResult(userService.selectUserWithPage(user,pageNum,pageSize));
    }

    @GetMapping("/find/{loginName}")
    public BaseResponse findUser(@PathVariable String loginName){
        return super.successResult(userService.findUser(loginName));
    }

    @PostMapping("/update")
    public BaseResponse updateUser(@RequestBody User user){
        return super.successResult(userService.updateUser(user));
    }
}

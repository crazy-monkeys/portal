package com.crazy.portal.controller.system;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.system.SubAgentVO;
import com.crazy.portal.bean.system.UserCustomerMappingBean;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.entity.system.UserCustomerMapping;
import com.crazy.portal.service.system.UserCustomerMappingService;
import com.crazy.portal.service.system.UserService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * @Desc:
 * @Author:
 * @Date:
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;
    @Resource
    private UserCustomerMappingService userCustomerMappingService;

    /**
     * 分页查询
     * @return
     */
    @PostMapping("/list")
    public BaseResponse selectAllUsers(@RequestBody(required = false) User user,
                                       @RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize){

        PageInfo<User> users = userService.selectUserWithPage(user,pageNum,pageSize);
        return super.successResult(users);
    }

    @GetMapping("/find/{loginName}")
    public BaseResponse findUser(@PathVariable String loginName){
        log.info(super.getCurrentUser().getLoginName());
        return super.successResult(userService.findUser(loginName));
    }


    /**
     * 登录名称是否可用
     * @return
     */
    @GetMapping(value = "/validLoginName/{loginName}")
    public BaseResponse checkLoginName(@PathVariable String loginName) {
        User user = userService.findUser(loginName);
        Map<String,Boolean> map = this.valid( user == null );
        return super.successResult(map);
    }

    /**
     * 密码重置
     * @throws Exception
     */
    @PostMapping(value = "/modifyPwd")
    public BaseResponse modifyPwd(@RequestParam String loginName,
                                  @RequestParam String oldPwd,
                                  @RequestParam String newPwd) {

        log.info("用户{} 进行修改密码操作",loginName);
        //重置密码
        userService.modifyPwd(loginName,oldPwd,newPwd,super.getCurrentUser().getId());
        return super.successResult();
    }

    /**
     * 密码重置
     * @throws Exception
     */
    @PostMapping(value = "/resetPwd/{loginName}")
    public BaseResponse resetPwd(@PathVariable String loginName) {
        User currentUser = super.getCurrentUser();
        log.info("管理员{} 进行重置 '{}' 用户的密码操作",currentUser.getLoginName(),loginName);
        //重置密码
        userService.resetUserPwd(loginName,currentUser);
        return super.successResult();
    }

    @PostMapping(value = "/mapping")
    public BaseResponse getList(@RequestBody UserCustomerMappingBean bean){
        return successResult(userCustomerMappingService.selectByPage(bean));
    }

    @PostMapping(value = "/createMapping")
    public BaseResponse createMapping(@RequestBody UserCustomerMapping mapping){
        userCustomerMappingService.saveOrUpdateMapping(mapping, this.getCurrentUserId());
        return successResult();
    }
    private Map<String, Boolean> valid(Boolean valid) {
        Map<String, Boolean> map = new HashMap<>();
        map.put("valid", valid);
        return map;
    }
}

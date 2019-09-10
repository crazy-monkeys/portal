package com.crazy.portal.controller.system;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.system.UserCustomerMappingBean;
import com.crazy.portal.config.email.EmailHelper;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.entity.system.UserCustomerMapping;
import com.crazy.portal.service.system.UserCustomerMappingService;
import com.crazy.portal.service.system.UserService;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.ErrorCodes;
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
    @GetMapping("/validLoginName/{loginName}")
    public BaseResponse checkLoginName(@PathVariable String loginName) {
        User user = userService.findUser(loginName);
        Map<String,Boolean> map = this.valid( user == null );
        return super.successResult(map);
    }

    /**
     * 修改密码
     * @throws Exception
     */
    @PostMapping("/modifyPwd")
    public BaseResponse modifyPwd(@RequestParam String loginName,
                                  @RequestParam String oldPwd,
                                  @RequestParam String newPwd) {

        log.info("用户{} 进行修改密码操作",loginName);
        //重置密码
        userService.modifyPwd(loginName,oldPwd,newPwd,super.getCurrentUser().getId());
        return super.successResult();
    }

    /**
     * 忘记密码-发送邮件
     * @param loginName
     * @return
     */
    @PostMapping("/forgetPwd/sendEmail/{loginName}")
    public BaseResponse sendEmail(@PathVariable String loginName){
        User user = userService.findUser(loginName);
        BusinessUtil.notNull(user, ErrorCodes.SystemManagerEnum.USER_NOT_EXISTS);

        userService.sendForgetEmail(loginName);
        return super.successResult();
    }

    /**
     * 忘记密码-修改密码
     * @param loginName
     * @param sid
     * @param newPwd
     * @return
     */
    @PostMapping("/forgetPwd/modifyPwd/{loginName}")
    public BaseResponse forgetPwd(@PathVariable String loginName,
                                  @RequestParam String sid,
                                  @RequestParam String newPwd){

        userService.modifyPwd(loginName,sid,newPwd);
        return successResult();
    }

    /**
     * 密码重置
     * @throws Exception
     */
    @PostMapping("/resetPwd/{loginName}")
    public BaseResponse resetPwd(@PathVariable String loginName) {
        User currentUser = super.getCurrentUser();
        log.info("管理员{} 进行重置 '{}' 用户的密码操作",currentUser.getLoginName(),loginName);
        //重置密码
        userService.resetUserPwd(loginName,currentUser);
        return super.successResult();
    }

    /**
     * 获取代理商的内外部客户
     * @return
     */
    @GetMapping(value = "/ships")
    public BaseResponse selectShips(){
        return successResult(userService.getUserShips(getCurrentUser().getDealerId()));
    }

    @PostMapping(value = "/mapping")
    public BaseResponse getList(@RequestBody UserCustomerMappingBean bean){
        return successResult(userCustomerMappingService.selectByPage(bean));
    }

    @PostMapping("/createMapping")
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

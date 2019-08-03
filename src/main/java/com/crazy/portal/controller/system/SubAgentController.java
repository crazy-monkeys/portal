package com.crazy.portal.controller.system;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.system.SubAgentVO;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.system.Role;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.service.system.RoleService;
import com.crazy.portal.service.system.UserService;
import com.crazy.portal.util.Enums;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 12:12 2019-08-03
 * @Modified by:
 */
@RestController
@RequestMapping("/subAgent")
public class SubAgentController extends BaseController {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;


    /**
     * 子代理商用户创建
     * 创建登陆账号
     * @param subAgentVO
     */
    @PostMapping("/regSubAgent")
    public BaseResponse regSubAgent(@Valid @RequestBody SubAgentVO subAgentVO){
        userService.regSubAgent(subAgentVO,super.getCurrentUser().getId());
        return super.successResult();
    }

    /**
     * 查询所有子账号角色类型的角色
     * @return
     */
    @GetMapping("/getSubRoles")
    public BaseResponse getSubRole(){
        int roleType = 2;
        List<Role> roles = roleService.findRoles(roleType);
        return super.successResult(roles);
    }

    /**
     * 获取子账号列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getSubAgents")
    public BaseResponse getSubAgents(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false,defaultValue = "10") Integer pageSize){

        Role role = super.getCurrentRole();
        User userQuery = new User();
        userQuery.setCreateUserId(role.getRoleCode().equals("ADMIN")?null:super.getCurrentUser().getId());
        userQuery.setUserType(Enums.USER_TYPE.subAgent.toString());
        PageInfo<User> users = userService.getSubAgents(userQuery,pageNum,pageSize);
        return super.successResult(users);
    }
}

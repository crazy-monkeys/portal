package com.crazy.portal.controller.system;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.system.Role;
import com.crazy.portal.service.system.RoleService;
import com.crazy.portal.util.ErrorCodes.SystemManagerEnum;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 22:49 2019/6/10
 * @Modified by:
 */
@RestController
@Slf4j
@RequestMapping("/permission")
public class RoleController extends BaseController {

    @Resource
    private RoleService roleService;

    /**
     * 获取当前页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/roleInfo")
    public BaseResponse userSetting(@RequestParam(required = false) String roleName,
                                    @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                    @RequestParam(required = false,defaultValue = "10") Integer pageSize) {
        PageInfo<Role> pager = roleService.queryRoleListPag(roleName, pageNum,pageSize);
        return super.successResult(pager);
    }

    /**Authorization
     * 新增角色
     * @return
     */
    @PostMapping(value = "/saveRole")
    public BaseResponse saveRole(@RequestBody Role role) {
        if(StringUtils.isEmpty(role.getRoleName())){
            return new BaseResponse(SystemManagerEnum.ROLE_EMPTY_NAME.getCode(),
                    SystemManagerEnum.ROLE_EMPTY_NAME.getZhMsg());
        }

        role.setActive((short)1);
        boolean isExist = roleService.roleExist(role.getRoleName());
        if(isExist){
            return new BaseResponse(SystemManagerEnum.ROLE_EXISTS.getCode(),
                    SystemManagerEnum.ROLE_EXISTS.getZhMsg());
        }
        role.setCreateTime(new Date());
        role.setCreateUserId(super.getCurrentUser().getId());
        roleService.saveRole(role);
        return super.successResult(role.getId());
    }

    /**
     * 查询角色详情
     * @param roleId
     * @return
     */
    @GetMapping(value = "/findRole/{roleId}")
    public BaseResponse findRole(@PathVariable Integer roleId) {
        Role role = roleService.findRole(roleId);
        if(role.getRoleName() == null){
            return new BaseResponse(SystemManagerEnum.ROLE_NOT_EXIST.getCode(),
                    SystemManagerEnum.ROLE_NOT_EXIST.getZhMsg());
        }
        return super.successResult(role);
    }

    /**
     * 修改角色
     * @return
     */
    @PostMapping(value = "/updateRole")
    public BaseResponse updateRole(@RequestBody Role role) {
        if(StringUtils.isEmpty(role.getRoleName())){
            return new BaseResponse(SystemManagerEnum.ROLE_EMPTY_NAME.getCode(),
                    SystemManagerEnum.ROLE_EMPTY_NAME.getZhMsg());
        }
        if(role.getId() == null){
            return new BaseResponse(SystemManagerEnum.ROLE_EMPTY_ID.getCode(),
                    SystemManagerEnum.ROLE_EMPTY_ID.getZhMsg());
        }
        Role currentRole = roleService.findRole(role.getId());
        if(currentRole.getRoleName() == null){
            return new BaseResponse(SystemManagerEnum.ROLE_NOT_EXIST.getCode(),
                    SystemManagerEnum.ROLE_NOT_EXIST.getZhMsg());
        }
        boolean hasExist = roleService.roleExist(role.getRoleName());
        if(!currentRole.getRoleName().equals(role.getRoleName()) && hasExist){
            return new BaseResponse(SystemManagerEnum.ROLE_EXISTS.getCode(),
                    SystemManagerEnum.ROLE_EXISTS.getZhMsg());
        }
        role.setUpdateTime(new Date());
        role.setUpdateUserId(super.getCurrentUser().getId());
        roleService.saveRole(role);
        return super.successResult(role.getId());
    }
}

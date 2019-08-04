package com.crazy.portal.controller.system;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.system.Role;
import com.crazy.portal.service.system.RoleService;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.ErrorCodes.SystemManagerEnum;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    @GetMapping(value = "/rolePageInfo")
    public BaseResponse userSetting(@RequestParam(required = false) String roleCode,
                                    @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                    @RequestParam(required = false,defaultValue = "10") Integer pageSize) {

        PageInfo<Role> pager = roleService.queryRoleListPag(roleCode, pageNum,pageSize);
        return super.successResult(pager);
    }

    @GetMapping(value = "/findRoles")
    public BaseResponse findRoles() {
        List<Role> roles = roleService.findRoles();
        return super.successResult(roles);
    }

    /**
     * 新增角色
     * @return
     */
    @PostMapping(value = "/saveRole")
    public BaseResponse saveRole(@RequestBody Role role) {
        BusinessUtil.assertEmpty(role.getRoleCode(),SystemManagerEnum.ROLE_EMPTY_CODE);
        BusinessUtil.assertEmpty(role.getRoleName(),SystemManagerEnum.ROLE_EMPTY_NAME);
        BusinessUtil.assertIsNull(role.getRoleType(),SystemManagerEnum.ROLE_EMPTY_TYPE);
        Role roleQuery = roleService.findRole(role.getRoleCode());
        BusinessUtil.assertIsNotNull(roleQuery,SystemManagerEnum.ROLE_EXISTS);
        role.setActive((short)1);
        role.setCreateTime(new Date());
        role.setCreateUserId(super.getCurrentUser().getId());
        roleService.saveRole(role);
        return super.successResult();
    }

    /**
     * 查询角色详情
     * @return
     */
    @GetMapping(value = "/findRole/{roleCode}")
    public BaseResponse findRole(@PathVariable String roleCode) {
        Role role = roleService.findRole(roleCode);
        BusinessUtil.assertIsNull(role,SystemManagerEnum.ROLE_NOT_EXIST);
        BusinessUtil.assertEmpty(role.getRoleName(),SystemManagerEnum.ROLE_NOT_EXIST);
        return super.successResult(role);
    }

    /**
     * 修改角色
     * @return
     */
    @PostMapping(value = "/updateRole")
    public BaseResponse updateRole(@RequestBody Role role) {
        BusinessUtil.assertIsNull(role.getId(),SystemManagerEnum.ROLE_EMPTY_ID);
        BusinessUtil.assertEmpty(role.getRoleCode(),SystemManagerEnum.ROLE_EMPTY_CODE);
        Role currentRole = roleService.findRole(role.getId());
        String currentRoleName = currentRole.getRoleName();
        String currentRoleCode = currentRole.getRoleCode();
        BusinessUtil.assertIsNull(currentRole,SystemManagerEnum.ROLE_NOT_EXIST);
        Role checkRoleName = roleService.findRoleByName(role.getRoleName());
        if(checkRoleName != null && !currentRoleName.equals(checkRoleName.getRoleName())){
            BusinessUtil.assertFlase(true,SystemManagerEnum.ROLE_EXISTS);
        }

        Role checkRoleCode = roleService.findRole(role.getRoleCode());
        if(checkRoleCode != null && !currentRoleCode.equals(checkRoleCode.getRoleCode())){
            BusinessUtil.assertFlase(true,SystemManagerEnum.ROLE_CODE_EXISTS);
        }
        role.setUpdateTime(new Date());
        role.setUpdateUserId(super.getCurrentUser().getId());
        roleService.saveRole(role);
        return super.successResult();
    }
}

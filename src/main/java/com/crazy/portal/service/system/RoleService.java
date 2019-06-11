package com.crazy.portal.service.system;

import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.system.RoleMapper;
import com.crazy.portal.dao.system.RoleResourceMapper;
import com.crazy.portal.entity.system.Role;
import com.crazy.portal.util.BeanUtils;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.PortalUtil;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 22:07 2019/6/7
 * @Modified by:
 */
@Service
@Slf4j
public class RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleResourceMapper roleResourceMapper;

    /**
     * 分页获取角色列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Role> queryRoleListPag(int pageNum,int pageSize){
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<Role> roleList = roleMapper.queryRoleList();
        Page<Role> page = new Page<>();
        page.addAll(roleList);
        return new PageInfo<>(page);
    }

    public boolean roleExist(String roleName){
        return roleMapper.countByRoleName(roleName)>0?true:false;
    }


    /**
     * 新增/更新 角色
     * @param role
     * @return
     */
    public int saveRole(Role role){
        try {
            if(role.getId() == null){
                return roleMapper.insert(role);
            }else{
                Role roleInDo = roleMapper.selectByPrimaryKey(role.getId());
                if(roleInDo != null){
                    BeanUtils.copyNotNullFields(role,roleInDo);
                    return roleMapper.updateByPrimaryKey(roleInDo);
                }
            }
        } catch (Exception e) {
            log.error("",e);
            throw new BusinessException(ErrorCodes.SystemManagerEnum.ROLE_SAVE_FAILED);
        }
        return 0;
    }

    /**
     * 查询role详情
     * @param roleId
     * @return
     */
    public Role findRole(Integer roleId){
        return null;
    }



    /**
     * 删除角色
     * @param roleId
     * @return
     */
    public int deleteRole(Integer roleId){
        return 0;
    }

}

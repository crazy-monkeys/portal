package com.crazy.portal.service.system;

import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.system.RoleMapper;
import com.crazy.portal.entity.system.Role;
import com.crazy.portal.util.BeanUtils;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    /**
     * 分页获取角色列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Role> queryRoleListPag(String roleCode, int pageNum,int pageSize){
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<Role> roleList = roleMapper.queryRoleList(roleCode);
        return new PageInfo<>(roleList);
    }


    /**
     * 新增/更新 角色
     * @param role
     * @return
     */
    public int saveRole(Role role){
        try {
            if(role.getId() == null){
                return roleMapper.insertSelective(role);
            }else{
                Role roleInDo = roleMapper.findById(role.getId());
                if(roleInDo != null){
                    BeanUtils.copyNotNullFields(role,roleInDo);
                    return roleMapper.updateByPrimaryKeySelective(roleInDo);
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
     * @param roleCode
     * @return
     */
    public Role findRole(String roleCode){
        return roleMapper.findRoleByCode(roleCode);
    }

    public Role findRole(Integer roleId){
        return roleMapper.findById(roleId);
    }

    public Role findRoleByName(String roleName){
        return roleMapper.findByName(roleName);
    }
    /**
     * 获取所有有效的角色
     * @return
     */
    public List<Role> findRoles(){
        return roleMapper.findAllRoles();
    }

    public List<Role> findRoles(Integer roleType){
        return roleMapper.findRoleByType(roleType);
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

package com.crazy.portal.service.system;

import com.crazy.portal.entity.system.Role;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 22:07 2019/6/7
 * @Modified by:
 */
@Service
public class RoleService {


    /**
     * 分页获取角色列表
     * @param page
     * @return
     */
    public PageInfo<Role> findRolesWithPage(Page page){
        return null;
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
     * 新增/更新 角色
     * @param role
     * @return
     */
    public int saveRole(Role role){
        return 0;
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

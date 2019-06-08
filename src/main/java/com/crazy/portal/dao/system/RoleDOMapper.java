package com.crazy.portal.dao.system;

import com.crazy.portal.entity.system.Role;

import java.util.List;

public interface RoleDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);


    /**
     * 查询角色列表
     * @return
     */
    List<Role> queryRoleList();

    /**
     * 查询用户所属的角色
     * @param userId
     * @return
     */
    List<Role> findRolesByUserId(Integer userId);


    /**
     * roleName是否存在
     * @param roleName
     * @return
     */
    int countByRoleName(String roleName);

    List<Role> findRolesByResourceId(Integer resourceId);
}
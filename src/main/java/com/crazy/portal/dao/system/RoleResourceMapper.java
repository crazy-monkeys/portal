package com.crazy.portal.dao.system;

import com.crazy.portal.entity.system.Role;
import com.crazy.portal.entity.system.RoleResource;

import java.util.List;

public interface RoleResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    RoleResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleResource record);

    int updateByPrimaryKey(RoleResource record);

    List<Integer> selectRoleResourceByRoleIds(List<Integer> roleIds);
}
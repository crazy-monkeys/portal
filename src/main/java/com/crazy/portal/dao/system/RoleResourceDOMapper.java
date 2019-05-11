package com.crazy.portal.dao.system;

import com.crazy.portal.entity.system.RoleResourceDO;

public interface RoleResourceDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleResourceDO record);

    int insertSelective(RoleResourceDO record);

    RoleResourceDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleResourceDO record);

    int updateByPrimaryKey(RoleResourceDO record);
}
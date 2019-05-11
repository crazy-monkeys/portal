package com.crazy.portal.dao.system;

import com.crazy.portal.entity.system.ResourceDO;

public interface ResourceDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResourceDO record);

    int insertSelective(ResourceDO record);

    ResourceDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourceDO record);

    int updateByPrimaryKey(ResourceDO record);
}
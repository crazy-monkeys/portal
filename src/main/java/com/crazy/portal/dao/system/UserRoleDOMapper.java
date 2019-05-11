package com.crazy.portal.dao.system;

import com.crazy.portal.entity.system.UserRoleDO;

public interface UserRoleDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRoleDO record);

    int insertSelective(UserRoleDO record);

    UserRoleDO selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(UserRoleDO record);

    int updateByPrimaryKey(UserRoleDO record);


}
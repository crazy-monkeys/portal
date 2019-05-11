package com.crazy.portal.dao.system;

import com.crazy.portal.entity.system.UserDO;

import java.util.List;

public interface UserDOMapper {

    UserDO selectById(Integer id);

    int insertSelective(UserDO record);

    int updateByPrimaryKeySelective(UserDO record);

    UserDO findByLoginName(String loginName);

    List<UserDO> selectAllUser();
}
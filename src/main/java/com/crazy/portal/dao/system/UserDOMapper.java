package com.crazy.portal.dao.system;

import com.crazy.portal.entity.system.User;

import java.util.List;

public interface UserDOMapper {

    User selectById(Integer id);

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

    User findByLoginName(String loginName);

    List<User> selectAllUser();
}
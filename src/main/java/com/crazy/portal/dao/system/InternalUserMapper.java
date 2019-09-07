package com.crazy.portal.dao.system;

import com.crazy.portal.entity.system.InternalUser;

public interface InternalUserMapper {
    int deleteByPrimaryKey(Integer inUserId);

    int insert(InternalUser record);

    int insertSelective(InternalUser record);

    InternalUser selectByPrimaryKey(Integer inUserId);

    int updateByPrimaryKeySelective(InternalUser record);

    int updateByPrimaryKey(InternalUser record);

    InternalUser selectByUserId(Integer userId);

    InternalUser selectByUserNo(String userNo);
}
package com.crazy.portal.dao.webservice;

import com.crazy.portal.entity.webservice.ApiUsers;

public interface ApiUsersMapper {
    int deleteByPrimaryKey(Integer apiUserId);

    int insert(ApiUsers record);

    int insertSelective(ApiUsers record);

    ApiUsers selectByPrimaryKey(Integer apiUserId);

    int updateByPrimaryKeySelective(ApiUsers record);

    int updateByPrimaryKey(ApiUsers record);
}
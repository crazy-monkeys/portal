package com.crazy.portal.dao.webservice;

import com.crazy.portal.entity.webservice.ApiLog;

public interface ApiLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApiLog record);

    int insertSelective(ApiLog record);

    ApiLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApiLog record);

    int updateByPrimaryKey(ApiLog record);
}
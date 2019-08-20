package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.cusotmer.CustomerAgent;

public interface CustomerAgentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerAgent record);

    int insertSelective(CustomerAgent record);

    CustomerAgent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerAgent record);

    int updateByPrimaryKey(CustomerAgent record);
}
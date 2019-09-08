package com.crazy.portal.dao.cusotmer;

import com.crazy.portal.entity.cusotmer.CustomerAgent;

import java.util.List;

public interface CustomerAgentMapper {
    int deleteByPrimaryKey(Integer agentId);

    int insert(CustomerAgent record);

    int insertSelective(CustomerAgent record);

    CustomerAgent selectByPrimaryKey(Integer agentId);

    int updateByPrimaryKeySelective(CustomerAgent record);

    int updateByPrimaryKey(CustomerAgent record);

    List<CustomerAgent> selectByCustId(Integer custId);

    int deleteByCustId(Integer custId);
}
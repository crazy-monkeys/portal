package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.customer.TCustomerProject;

public interface TCustomerProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCustomerProject record);

    int insertSelective(TCustomerProject record);

    TCustomerProject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCustomerProject record);

    int updateByPrimaryKey(TCustomerProject record);
}
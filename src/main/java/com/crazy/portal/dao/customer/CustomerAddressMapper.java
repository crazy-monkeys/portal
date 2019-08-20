package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.cusotmer.CustomerAddress;

public interface CustomerAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerAddress record);

    int insertSelective(CustomerAddress record);

    CustomerAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerAddress record);

    int updateByPrimaryKey(CustomerAddress record);
}
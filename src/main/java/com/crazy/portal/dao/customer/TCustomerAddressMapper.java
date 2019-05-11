package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.customer.TCustomerAddress;

public interface TCustomerAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCustomerAddress record);

    int insertSelective(TCustomerAddress record);

    TCustomerAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCustomerAddress record);

    int updateByPrimaryKey(TCustomerAddress record);
}
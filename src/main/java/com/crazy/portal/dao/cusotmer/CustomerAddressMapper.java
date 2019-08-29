package com.crazy.portal.dao.cusotmer;

import com.crazy.portal.entity.cusotmer.CustomerAddress;

import java.util.List;

public interface CustomerAddressMapper {
    int deleteByPrimaryKey(Integer addressId);

    int insert(CustomerAddress record);

    int insertSelective(CustomerAddress record);

    CustomerAddress selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(CustomerAddress record);

    int updateByPrimaryKey(CustomerAddress record);

    List<CustomerAddress> selectByCustId(Integer custId);

    int deleteByCustId(Integer custId);
}
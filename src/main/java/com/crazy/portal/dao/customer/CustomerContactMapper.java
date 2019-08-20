package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.cusotmer.CustomerContact;

public interface CustomerContactMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerContact record);

    int insertSelective(CustomerContact record);

    CustomerContact selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerContact record);

    int updateByPrimaryKey(CustomerContact record);
}
package com.crazy.portal.dao.cusotmer;

import com.crazy.portal.entity.cusotmer.CustomerContact;

public interface CustomerContactMapper {
    int deleteByPrimaryKey(Integer contactId);

    int insert(CustomerContact record);

    int insertSelective(CustomerContact record);

    CustomerContact selectByPrimaryKey(Integer contactId);

    int updateByPrimaryKeySelective(CustomerContact record);

    int updateByPrimaryKey(CustomerContact record);
}
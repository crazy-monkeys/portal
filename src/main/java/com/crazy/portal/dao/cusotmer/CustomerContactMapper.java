package com.crazy.portal.dao.cusotmer;

import com.crazy.portal.entity.cusotmer.CustomerContact;

import java.util.List;

public interface CustomerContactMapper {
    int deleteByPrimaryKey(Integer contactId);

    int insert(CustomerContact record);

    int insertSelective(CustomerContact record);

    CustomerContact selectByPrimaryKey(Integer contactId);

    int updateByPrimaryKeySelective(CustomerContact record);

    int updateByPrimaryKey(CustomerContact record);

    List<CustomerContact> selectByCustId(Integer custId);

    List<CustomerContact> selectByCustName(String custName);

    int deleteByCustId(Integer custId);
}
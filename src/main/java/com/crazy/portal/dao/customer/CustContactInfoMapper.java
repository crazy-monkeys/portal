package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.customer.CustContactInfo;

public interface CustContactInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustContactInfo record);

    int insertSelective(CustContactInfo record);

    CustContactInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustContactInfo record);

    int updateByPrimaryKey(CustContactInfo record);
}
package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.customer.CustEquityInfo;

public interface CustEquityInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustEquityInfo record);

    int insertSelective(CustEquityInfo record);

    CustEquityInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustEquityInfo record);

    int updateByPrimaryKey(CustEquityInfo record);
}
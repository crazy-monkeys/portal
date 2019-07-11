package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.customer.CustBankInfo;

public interface CustBankInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustBankInfo record);

    int insertSelective(CustBankInfo record);

    CustBankInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustBankInfo record);

    int updateByPrimaryKey(CustBankInfo record);
}
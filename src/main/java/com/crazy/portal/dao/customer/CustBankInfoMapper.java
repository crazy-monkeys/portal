package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.cusotmer.CustBankInfo;

public interface CustBankInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustBankInfo record);

    int insertSelective(CustBankInfo record);

    CustBankInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustBankInfo record);

    int updateByPrimaryKeyWithBLOBs(CustBankInfo record);

    int updateByPrimaryKey(CustBankInfo record);
}
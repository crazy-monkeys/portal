package com.crazy.portal.dao.cusotmer;

import com.crazy.portal.entity.cusotmer.CustBankInfo;

public interface CustBankInfoMapper {
    int deleteByPrimaryKey(Integer bankId);

    int insert(CustBankInfo record);

    int insertSelective(CustBankInfo record);

    CustBankInfo selectByPrimaryKey(Integer bankId);

    int updateByPrimaryKeySelective(CustBankInfo record);

    int updateByPrimaryKeyWithBLOBs(CustBankInfo record);

    int updateByPrimaryKey(CustBankInfo record);

    CustBankInfo selectByCustId(Integer custId);
}
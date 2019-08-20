package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.cusotmer.CustInvoiceInfo;

public interface CustInvoiceInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustInvoiceInfo record);

    int insertSelective(CustInvoiceInfo record);

    CustInvoiceInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustInvoiceInfo record);

    int updateByPrimaryKey(CustInvoiceInfo record);
}
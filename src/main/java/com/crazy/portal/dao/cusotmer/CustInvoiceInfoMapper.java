package com.crazy.portal.dao.cusotmer;

import com.crazy.portal.entity.cusotmer.CustInvoiceInfo;

public interface CustInvoiceInfoMapper {
    int deleteByPrimaryKey(Integer invoiceId);

    int insert(CustInvoiceInfo record);

    int insertSelective(CustInvoiceInfo record);

    CustInvoiceInfo selectByPrimaryKey(Integer invoiceId);

    int updateByPrimaryKeySelective(CustInvoiceInfo record);

    int updateByPrimaryKey(CustInvoiceInfo record);
}
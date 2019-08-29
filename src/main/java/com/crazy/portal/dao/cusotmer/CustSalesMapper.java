package com.crazy.portal.dao.cusotmer;

import com.crazy.portal.entity.cusotmer.CustSales;

public interface CustSalesMapper {
    int deleteByPrimaryKey(Integer salesId);

    int insert(CustSales record);

    int insertSelective(CustSales record);

    CustSales selectByPrimaryKey(Integer salesId);

    int updateByPrimaryKeySelective(CustSales record);

    int updateByPrimaryKey(CustSales record);
}
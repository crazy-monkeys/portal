package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.cusotmer.CustSales;

public interface CustSalesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustSales record);

    int insertSelective(CustSales record);

    CustSales selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustSales record);

    int updateByPrimaryKey(CustSales record);
}
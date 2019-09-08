package com.crazy.portal.dao.cusotmer;

import com.crazy.portal.entity.cusotmer.CustSalesQuota;

import java.util.List;

public interface CustSalesQuotaMapper {
    int deleteByPrimaryKey(Integer quoatId);

    int insert(CustSalesQuota record);

    int insertSelective(CustSalesQuota record);

    CustSalesQuota selectByPrimaryKey(Integer quoatId);

    int updateByPrimaryKeySelective(CustSalesQuota record);

    int updateByPrimaryKey(CustSalesQuota record);

    int deleteByCustId(Integer custId);

    List<CustSalesQuota> selectByCustId(Integer custId);
}
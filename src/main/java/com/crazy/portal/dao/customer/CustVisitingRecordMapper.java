package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.customer.CustVisitingRecord;

public interface CustVisitingRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustVisitingRecord record);

    int insertSelective(CustVisitingRecord record);

    CustVisitingRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustVisitingRecord record);

    int updateByPrimaryKey(CustVisitingRecord record);
}
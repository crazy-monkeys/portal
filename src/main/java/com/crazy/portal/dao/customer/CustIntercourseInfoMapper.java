package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.customer.CustIntercourseInfo;

public interface CustIntercourseInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustIntercourseInfo record);

    int insertSelective(CustIntercourseInfo record);

    CustIntercourseInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustIntercourseInfo record);

    int updateByPrimaryKey(CustIntercourseInfo record);
}
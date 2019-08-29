package com.crazy.portal.dao.cusotmer;

import com.crazy.portal.entity.cusotmer.CustBusinessInformation;

public interface CustBusinessInformationMapper {
    int deleteByPrimaryKey(Integer busInfoId);

    int insert(CustBusinessInformation record);

    int insertSelective(CustBusinessInformation record);

    CustBusinessInformation selectByPrimaryKey(Integer busInfoId);

    int updateByPrimaryKeySelective(CustBusinessInformation record);

    int updateByPrimaryKey(CustBusinessInformation record);
}
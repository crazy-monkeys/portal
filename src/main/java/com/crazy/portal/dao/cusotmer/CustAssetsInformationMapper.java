package com.crazy.portal.dao.cusotmer;

import com.crazy.portal.entity.cusotmer.CustAssetsInformation;

public interface CustAssetsInformationMapper {
    int deleteByPrimaryKey(Integer asseteInfoId);

    int insert(CustAssetsInformation record);

    int insertSelective(CustAssetsInformation record);

    CustAssetsInformation selectByPrimaryKey(Integer asseteInfoId);

    int updateByPrimaryKeySelective(CustAssetsInformation record);

    int updateByPrimaryKey(CustAssetsInformation record);
}
package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.cusotmer.AssetsInformation;

public interface AssetsInformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AssetsInformation record);

    int insertSelective(AssetsInformation record);

    AssetsInformation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AssetsInformation record);

    int updateByPrimaryKey(AssetsInformation record);
}
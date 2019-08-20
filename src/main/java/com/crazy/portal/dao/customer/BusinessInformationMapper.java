package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.cusotmer.BusinessInformation;

public interface BusinessInformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessInformation record);

    int insertSelective(BusinessInformation record);

    BusinessInformation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessInformation record);

    int updateByPrimaryKey(BusinessInformation record);
}
package com.crazy.portal.dao.company;

import com.crazy.portal.entity.company.TSolutionInfoVO;

public interface TSolutionInfoVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TSolutionInfoVO record);

    int insertSelective(TSolutionInfoVO record);

    TSolutionInfoVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TSolutionInfoVO record);

    int updateByPrimaryKey(TSolutionInfoVO record);
}
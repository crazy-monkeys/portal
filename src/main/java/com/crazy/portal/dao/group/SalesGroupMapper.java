package com.crazy.portal.dao.group;

import com.crazy.portal.entity.group.SalesGroup;

import java.util.List;

public interface SalesGroupMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(SalesGroup record);

    int insertSelective(SalesGroup record);

    SalesGroup selectByPrimaryKey(Integer groupId);

    int updateByPrimaryKeySelective(SalesGroup record);

    int updateByPrimaryKey(SalesGroup record);

    List<SalesGroup> selectSalesGroup();
}
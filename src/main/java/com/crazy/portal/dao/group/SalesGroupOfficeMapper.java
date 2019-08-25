package com.crazy.portal.dao.group;

import com.crazy.portal.entity.group.SalesGroupOffice;

public interface SalesGroupOfficeMapper {
    int deleteByPrimaryKey(Integer officeId);

    int insert(SalesGroupOffice record);

    int insertSelective(SalesGroupOffice record);

    SalesGroupOffice selectByPrimaryKey(Integer officeId);

    int updateByPrimaryKeySelective(SalesGroupOffice record);

    int updateByPrimaryKey(SalesGroupOffice record);
}
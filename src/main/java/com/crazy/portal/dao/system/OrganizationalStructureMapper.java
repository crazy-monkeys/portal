package com.crazy.portal.dao.system;

import com.crazy.portal.entity.system.OrganizationalStructure;

public interface OrganizationalStructureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrganizationalStructure record);

    int insertSelective(OrganizationalStructure record);

    OrganizationalStructure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrganizationalStructure record);

    int updateByPrimaryKey(OrganizationalStructure record);

    OrganizationalStructure selectByOrgNo(Integer seq);

    OrganizationalStructure selectSalesOrg(String seq);


}
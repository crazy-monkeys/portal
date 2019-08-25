package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.cusotmer.CustCorporateRelationship;

import java.util.List;

public interface CustCorporateRelationshipMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustCorporateRelationship record);

    int insertSelective(CustCorporateRelationship record);

    CustCorporateRelationship selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustCorporateRelationship record);

    int updateByPrimaryKey(CustCorporateRelationship record);

    List<CustCorporateRelationship> selectDealerShip(Integer dealerId);
}
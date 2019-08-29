package com.crazy.portal.dao.cusotmer;

import com.crazy.portal.entity.cusotmer.CustCorporateRelationship;

import java.util.List;

public interface CustCorporateRelationshipMapper {
    int deleteByPrimaryKey(Integer shipId);

    int insert(CustCorporateRelationship record);

    int insertSelective(CustCorporateRelationship record);

    CustCorporateRelationship selectByPrimaryKey(Integer shipId);

    int updateByPrimaryKeySelective(CustCorporateRelationship record);

    int updateByPrimaryKey(CustCorporateRelationship record);

    List<CustCorporateRelationship> selectDealerShip(Integer dealerId);

    List<CustCorporateRelationship> selectByCustId(Integer custId);

    int deleteByCustId(Integer custId);
}
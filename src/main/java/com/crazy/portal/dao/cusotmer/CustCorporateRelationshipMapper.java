package com.crazy.portal.dao.cusotmer;

import com.crazy.portal.entity.cusotmer.CustCorporateRelationship;
import com.crazy.portal.entity.system.InternalUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustCorporateRelationshipMapper {
    int deleteByPrimaryKey(Integer shipId);

    int insert(CustCorporateRelationship record);

    int insertSelective(CustCorporateRelationship record);

    CustCorporateRelationship selectByPrimaryKey(Integer shipId);

    int updateByPrimaryKeySelective(CustCorporateRelationship record);

    int updateByPrimaryKey(CustCorporateRelationship record);

    List<CustCorporateRelationship> selectDealerShip(Integer dealerId);

    List<CustCorporateRelationship> selectDealerShipByName(String dealerName);

    List<CustCorporateRelationship> selectByCustId(Integer custId);

    int deleteByCustId(Integer custId);

    List<CustCorporateRelationship> selectZShip(Integer custId);

    CustCorporateRelationship selectInCustomer(Integer custId);

    List<CustCorporateRelationship> selectOutCustomer(String inCode);

    List<CustCorporateRelationship> selectDealerCustomer(@Param("dealerId")Integer dealerId, @Param("custAbb")String custAbb);

    CustCorporateRelationship selectByShipCus(@Param("custId") Integer custId, @Param("type") String type, @Param("inCode") String inCode);
}
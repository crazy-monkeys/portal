package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.BasicCorporateRelationship;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BasicCorporateRelationshipMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BasicCorporateRelationship record);

    BasicCorporateRelationship selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasicCorporateRelationship record);

    List<BasicCorporateRelationship> selectByCustId(@Param("custId")Integer custId);

    List<Integer> selectIdsByCustId(@Param("custId") Integer custId);

    int deleteByCustId(@Param("custId") Integer custId, @Param("userId")Integer userId);
}
package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.TBasicCorporateRelationshipDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TBasicCorporateRelationshipDOMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TBasicCorporateRelationshipDO record);

    TBasicCorporateRelationshipDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBasicCorporateRelationshipDO record);

    List<TBasicCorporateRelationshipDO> selectByCustId(@Param("custId")Integer custId);

    List<Integer> selectIdsByCustId(@Param("custId") Integer custId);

    int deleteByCustId(@Param("custId") Integer custId, @Param("userId")Integer userId);
}
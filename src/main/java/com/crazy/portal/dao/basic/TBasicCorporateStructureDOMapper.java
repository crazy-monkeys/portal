package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.TBasicCorporateStructureDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TBasicCorporateStructureDOMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TBasicCorporateStructureDO record);

    TBasicCorporateStructureDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBasicCorporateStructureDO record);

    List<TBasicCorporateStructureDO> selectByCustId(@Param("custId")Integer custId);

    List<Integer> selectIdsByCustId(@Param("custId") Integer custId);

    int deleteByCustId(@Param("custId") Integer custId, @Param("userId")Integer userId);

}
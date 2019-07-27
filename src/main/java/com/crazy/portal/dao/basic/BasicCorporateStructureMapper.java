package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.BasicCorporateStructure;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BasicCorporateStructureMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BasicCorporateStructure record);

    BasicCorporateStructure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasicCorporateStructure record);

    List<BasicCorporateStructure> selectByCustId(@Param("custId")Integer custId);

    List<Integer> selectIdsByCustId(@Param("custId") Integer custId);

    int deleteByCustId(@Param("custId") Integer custId, @Param("userId")Integer userId);

}
package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.TBasicSalesTeamDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TBasicSalesTeamDOMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TBasicSalesTeamDO record);

    TBasicSalesTeamDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBasicSalesTeamDO record);

    List<TBasicSalesTeamDO> selectByCustId(@Param("custId")Integer custId);

    List<Integer> selectIdsByCustId(@Param("custId") Integer custId);

    int deleteByCustId(@Param("custId") Integer custId, @Param("userId")Integer userId);
}
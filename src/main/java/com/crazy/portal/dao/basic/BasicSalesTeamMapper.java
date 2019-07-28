package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.BasicSalesTeam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BasicSalesTeamMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BasicSalesTeam record);

    BasicSalesTeam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasicSalesTeam record);

    List<BasicSalesTeam> selectByCustId(@Param("custId")Integer custId);

    List<Integer> selectIdsByCustId(@Param("custId") Integer custId);

    int deleteByCustId(@Param("custId") Integer custId, @Param("userId")Integer userId);
}
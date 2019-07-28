package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.BasicSales;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BasicSalesMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BasicSales record);

    BasicSales selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasicSales record);

    List<BasicSales> selectByCustId(@Param("custId")Integer custId);

    List<Integer> selectIdsByCustId(@Param("custId") Integer custId);

    int deleteByCustId(@Param("custId") Integer custId, @Param("userId")Integer userId);
}
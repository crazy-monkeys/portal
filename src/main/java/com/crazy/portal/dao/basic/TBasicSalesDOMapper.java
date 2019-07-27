package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.TBasicSalesDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TBasicSalesDOMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TBasicSalesDO record);

    TBasicSalesDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBasicSalesDO record);

    List<TBasicSalesDO> selectByCustId(@Param("custId")Integer custId);

    List<Integer> selectIdsByCustId(@Param("custId") Integer custId);

    int deleteByCustId(@Param("custId") Integer custId, @Param("userId")Integer userId);
}
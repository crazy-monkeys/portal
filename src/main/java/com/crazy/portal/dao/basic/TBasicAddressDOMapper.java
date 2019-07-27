package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.TBasicAddressDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TBasicAddressDOMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TBasicAddressDO record);

    TBasicAddressDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBasicAddressDO record);

    List<TBasicAddressDO> selectByCustId(@Param("custId")Integer custId);

    List<Integer> selectIdsByCustId(@Param("custId") Integer custId);

    int deleteByCustId(@Param("custId") Integer custId, @Param("userId")Integer userId);
}
package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.TBasicFileDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TBasicFileDOMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TBasicFileDO record);

    TBasicFileDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBasicFileDO record);

    List<TBasicFileDO> selectByCustId(@Param("custId")Integer custId);

    List<Integer> selectIdsByCustId(@Param("custId") Integer custId);

    int deleteByCustId(@Param("custId") Integer custId, @Param("userId")Integer userId);
}
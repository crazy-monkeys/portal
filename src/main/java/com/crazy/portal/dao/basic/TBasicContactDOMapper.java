package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.TBasicContactDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TBasicContactDOMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TBasicContactDO record);

    TBasicContactDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBasicContactDO record);

    List<TBasicContactDO> selectByCustId(@Param("custId")Integer custId);

    List<Integer> selectIdsByCustId(@Param("custId") Integer custId);

    int deleteByCustId(@Param("custId") Integer custId, @Param("userId")Integer userId);

}
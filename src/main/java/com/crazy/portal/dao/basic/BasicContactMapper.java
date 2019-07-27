package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.BasicContact;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BasicContactMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BasicContact record);

    BasicContact selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasicContact record);

    List<BasicContact> selectByCustId(@Param("custId")Integer custId);

    List<Integer> selectIdsByCustId(@Param("custId") Integer custId);

    int deleteByCustId(@Param("custId") Integer custId, @Param("userId")Integer userId);

}
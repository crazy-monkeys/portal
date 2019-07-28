package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.BasicAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BasicAddressMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BasicAddress record);

    BasicAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasicAddress record);

    List<BasicAddress> selectByCustId(@Param("custId")Integer custId);

    List<Integer> selectIdsByCustId(@Param("custId") Integer custId);

    int deleteByCustId(@Param("custId") Integer custId, @Param("userId")Integer userId);
}
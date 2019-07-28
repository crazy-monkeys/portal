package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.BasicBankInfo;
import org.apache.ibatis.annotations.Param;


public interface BasicBankInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BasicBankInfo record);

    BasicBankInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasicBankInfo record);

    BasicBankInfo selectByCustId(@Param("custId")Integer custId);

    int deleteByCustId(@Param("custId") Integer custId, @Param("userId")Integer userId);
}
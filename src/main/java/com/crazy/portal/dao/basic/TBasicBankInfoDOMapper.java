package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.TBasicBankInfoDO;
import org.apache.ibatis.annotations.Param;


public interface TBasicBankInfoDOMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TBasicBankInfoDO record);

    TBasicBankInfoDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBasicBankInfoDO record);

    TBasicBankInfoDO selectByCustId(@Param("custId")Integer custId);

    int deleteByCustId(@Param("custId") Integer custId, @Param("userId")Integer userId);
}
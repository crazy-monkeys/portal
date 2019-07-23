package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.TBasicBankInfoDO;

public interface TBasicBankInfoDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBasicBankInfoDO record);

    int insertSelective(TBasicBankInfoDO record);

    TBasicBankInfoDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBasicBankInfoDO record);

    int updateByPrimaryKeyWithBLOBs(TBasicBankInfoDO record);

    int updateByPrimaryKey(TBasicBankInfoDO record);
}
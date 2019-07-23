package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.TBasicAddressDO;

public interface TBasicAddressDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBasicAddressDO record);

    int insertSelective(TBasicAddressDO record);

    TBasicAddressDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBasicAddressDO record);

    int updateByPrimaryKeyWithBLOBs(TBasicAddressDO record);

    int updateByPrimaryKey(TBasicAddressDO record);
}
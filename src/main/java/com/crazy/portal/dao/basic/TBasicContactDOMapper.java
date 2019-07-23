package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.TBasicContactDO;

public interface TBasicContactDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBasicContactDO record);

    int insertSelective(TBasicContactDO record);

    TBasicContactDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBasicContactDO record);

    int updateByPrimaryKey(TBasicContactDO record);
}
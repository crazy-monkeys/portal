package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.TBasicLableDO;

public interface TBasicLableDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBasicLableDO record);

    int insertSelective(TBasicLableDO record);

    TBasicLableDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBasicLableDO record);

    int updateByPrimaryKey(TBasicLableDO record);
}
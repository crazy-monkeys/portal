package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.TBasicFileDO;

public interface TBasicFileDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBasicFileDO record);

    int insertSelective(TBasicFileDO record);

    TBasicFileDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBasicFileDO record);

    int updateByPrimaryKey(TBasicFileDO record);
}
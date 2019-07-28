package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.BaseEntity;

import java.util.List;

public interface BaseMapper {

    List<? extends BaseEntity> selectByCustId(Integer custId);

    List<Integer> selectIdsByCustId(Integer custId);

    int updateByPrimaryKeySelective(BaseEntity record);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(BaseEntity record);
}
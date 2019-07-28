package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.BasicLable;

public interface BasicLableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BasicLable record);

    int insertSelective(BasicLable record);

    BasicLable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasicLable record);

    int updateByPrimaryKey(BasicLable record);
}
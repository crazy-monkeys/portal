package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.BasicProduct;

public interface BasicProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BasicProduct record);

    BasicProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasicProduct record);

}
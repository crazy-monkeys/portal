package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.cusotmer.CustomerFile;

public interface CustomerFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerFile record);

    int insertSelective(CustomerFile record);

    CustomerFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerFile record);

    int updateByPrimaryKey(CustomerFile record);
}
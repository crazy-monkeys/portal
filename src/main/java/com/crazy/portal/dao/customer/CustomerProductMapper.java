package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.cusotmer.CustomerProduct;

public interface CustomerProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerProduct record);

    int insertSelective(CustomerProduct record);

    CustomerProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerProduct record);

    int updateByPrimaryKey(CustomerProduct record);
}
package com.crazy.portal.dao.cusotmer;

import com.crazy.portal.entity.cusotmer.CustomerProduct;

import java.util.List;

public interface CustomerProductMapper {
    int deleteByPrimaryKey(Integer proId);

    int insert(CustomerProduct record);

    int insertSelective(CustomerProduct record);

    CustomerProduct selectByPrimaryKey(Integer proId);

    int updateByPrimaryKeySelective(CustomerProduct record);

    int updateByPrimaryKey(CustomerProduct record);

    List<CustomerProduct> selectByCustId(Integer custId);

    int deleteByCustId(Integer custId);
}
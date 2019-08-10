package com.crazy.portal.dao.product;

import com.crazy.portal.entity.product.ProductSubDO;

public interface ProductSubDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductSubDO record);

    int insertSelective(ProductSubDO record);

    ProductSubDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductSubDO record);

    int updateByPrimaryKey(ProductSubDO record);
}
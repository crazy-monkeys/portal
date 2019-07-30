package com.crazy.portal.dao.product;

import com.crazy.portal.entity.product.ProductInfoDO;

public interface ProductInfoDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductInfoDO record);

    int insertSelective(ProductInfoDO record);

    ProductInfoDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductInfoDO record);

    int updateByPrimaryKey(ProductInfoDO record);
}
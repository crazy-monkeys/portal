package com.crazy.portal.dao.product;

import com.crazy.portal.entity.product.ProductInfoMpq;

public interface ProductInfoMpqMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductInfoMpq record);

    int insertSelective(ProductInfoMpq record);

    ProductInfoMpq selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductInfoMpq record);

    int updateByPrimaryKey(ProductInfoMpq record);

    ProductInfoMpq selectByProduct(String product);
}
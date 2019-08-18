package com.crazy.portal.dao.price;

import com.crazy.portal.entity.price.CatalogPrice;

public interface CatalogPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CatalogPrice record);

    CatalogPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CatalogPrice record);

}
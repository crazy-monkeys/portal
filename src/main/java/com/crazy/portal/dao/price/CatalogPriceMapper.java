package com.crazy.portal.dao.price;

import com.crazy.portal.bean.price.CatalogPriceVO;
import com.crazy.portal.entity.price.CatalogPrice;
import com.github.pagehelper.Page;

public interface CatalogPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CatalogPrice record);

    CatalogPrice selectByPrimaryKey(Integer id);

    Page<CatalogPrice> selectByParams(CatalogPriceVO record);

    int updateByPrimaryKeySelective(CatalogPrice record);

}
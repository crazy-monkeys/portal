package com.crazy.portal.dao.price;

import com.crazy.portal.bean.price.PriceStrategyVO;
import com.crazy.portal.entity.price.PriceStrategy;
import com.github.pagehelper.Page;

public interface PriceStrategyMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(PriceStrategy record);

    PriceStrategy selectByPrimaryKey(Integer id);

    Page<PriceStrategy> selectByParamsWithPage(PriceStrategyVO priceStrategyVO);

    int updateByPrimaryKeySelective(PriceStrategy record);
}
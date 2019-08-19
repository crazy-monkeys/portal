package com.crazy.portal.dao.price;

import com.crazy.portal.bean.price.ActualPriceVO;
import com.crazy.portal.entity.price.ActualPrice;
import com.github.pagehelper.Page;

public interface ActualPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ActualPrice record);

    ActualPrice selectByPrimaryKey(Integer id);

    Page<ActualPrice> selectByParamsWithPage(ActualPriceVO actualPriceVO);

    int updateByPrimaryKeySelective(ActualPrice record);
}
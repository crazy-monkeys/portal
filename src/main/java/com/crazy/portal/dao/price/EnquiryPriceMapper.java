package com.crazy.portal.dao.price;

import com.crazy.portal.bean.price.EnquiryPriceVO;
import com.crazy.portal.entity.price.EnquiryPrice;
import com.github.pagehelper.Page;

public interface EnquiryPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(EnquiryPrice record);

    EnquiryPrice selectByPrimaryKey(Integer id);

    Page<EnquiryPrice> selectByParamsWithPage(EnquiryPriceVO enquiryPriceVO);

    int updateByPrimaryKeySelective(EnquiryPrice record);
}
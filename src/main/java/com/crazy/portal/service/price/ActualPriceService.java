package com.crazy.portal.service.price;

import com.crazy.portal.bean.price.ActualPriceVO;
import com.crazy.portal.dao.price.ActualPriceMapper;
import com.crazy.portal.entity.price.ActualPrice;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 16:15 2019-08-18
 * @Modified by:
 */
@Service
public class ActualPriceService {

    @Resource
    private ActualPriceMapper actualPriceMapper;

    public PageInfo<ActualPrice> selectWithPage(ActualPriceVO actualPriceVO){
        PortalUtil.defaultStartPage(actualPriceVO.getPageIndex(), actualPriceVO.getPageSize());
        Page<ActualPrice> actualPrices = actualPriceMapper.selectByParamsWithPage(actualPriceVO);
        return new PageInfo<>(actualPrices);
    }
}

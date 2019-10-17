package com.crazy.portal.service.price;

import com.crazy.portal.bean.price.PriceStrategyVO;
import com.crazy.portal.dao.price.PriceStrategyMapper;
import com.crazy.portal.entity.price.PriceStrategy;
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
public class PriceStrategyService {

    @Resource
    private PriceStrategyMapper priceStrategyMapper;

    public PageInfo<PriceStrategy> selectWithPage(PriceStrategyVO priceStrategyVO){
        PortalUtil.defaultStartPage(priceStrategyVO.getPageIndex(), priceStrategyVO.getPageSize());
        Page<PriceStrategy> priceStrategies = priceStrategyMapper.selectByParamsWithPage(priceStrategyVO);
        return new PageInfo<>(priceStrategies);
    }
}

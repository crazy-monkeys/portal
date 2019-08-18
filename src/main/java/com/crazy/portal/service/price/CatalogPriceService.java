package com.crazy.portal.service.price;

import com.crazy.portal.bean.price.CatalogPriceVO;
import com.crazy.portal.dao.price.CatalogPriceMapper;
import com.crazy.portal.entity.price.CatalogPrice;
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
public class CatalogPriceService {

    @Resource
    private CatalogPriceMapper catalogPriceMapper;

    public PageInfo<CatalogPrice> selectWithPage(CatalogPriceVO catalogPriceVO){
        PortalUtil.defaultStartPage(catalogPriceVO.getPageIndex(), catalogPriceVO.getPageSize());
        Page<CatalogPrice> catalogPrices = catalogPriceMapper.selectByParams(catalogPriceVO);
        return new PageInfo<>(catalogPrices);
    }
}

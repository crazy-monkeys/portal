package com.crazy.portal.controller.price;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.price.CatalogPriceVO;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.price.CatalogPrice;
import com.crazy.portal.service.price.CatalogPriceService;
import com.crazy.portal.util.Enums;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @Desc: 目录价格
 * @Author: Bill
 * @Date: created in 15:39 2019-08-18
 * @Modified by:
 */
@RestController
@RequestMapping("/price/catalog")
public class CatalogPriceController extends BaseController {

    @Resource
    private CatalogPriceService catalogPriceService;

    @PostMapping("/query")
    public BaseResponse list(@RequestBody CatalogPriceVO catalogPriceVO){
        if(super.getCurrentUser().getUserType().equals(Enums.USER_TYPE.internal.toString())){
            catalogPriceVO.setUserType(Enums.USER_TYPE.internal.toString());
        }
        PageInfo<CatalogPrice> catalogPricePageInfo =  catalogPriceService.selectWithPage(catalogPriceVO);
        return super.successResult(catalogPricePageInfo);
    }

    @PostMapping("/detail")
    public BaseResponse detail(@RequestBody CatalogPriceVO catalogPriceVO){
        String productModel = catalogPriceVO.getProductModel();
        String inCustomer = catalogPriceVO.getInCustomer();
        String bu = catalogPriceVO.getBu();

        CatalogPrice catalogPrice = catalogPriceService.findCatalogPrice(productModel,bu,inCustomer);
        return super.successResult(catalogPrice);
    }
}

package com.crazy.portal.controller.price;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.price.PriceStrategyVO;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.price.PriceStrategy;
import com.crazy.portal.service.price.PriceStrategyService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @Desc: 产品价格策略
 * @Author: Bill
 * @Date: created in 15:42 2019-08-18
 * @Modified by:
 */
@RestController
@RequestMapping("/price/strategy")
public class PriceStrategyController extends BaseController {

    @Resource
    private PriceStrategyService priceStrategyService;

    @PostMapping("/query")
    public BaseResponse list(@RequestBody PriceStrategyVO priceStrategyVO){
        PageInfo<PriceStrategy> priceStrategyPageInfo = priceStrategyService.selectWithPage(priceStrategyVO);
        return super.successResult(priceStrategyPageInfo);
    }
}

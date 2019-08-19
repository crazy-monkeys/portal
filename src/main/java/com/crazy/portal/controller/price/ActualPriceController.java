package com.crazy.portal.controller.price;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.price.ActualPriceVO;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.price.ActualPrice;
import com.crazy.portal.service.price.ActualPriceService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @Desc: 实际价格
 * @Author: Bill
 * @Date: created in 15:41 2019-08-18
 * @Modified by:
 */
@RestController
@RequestMapping("/price/actual")
public class ActualPriceController extends BaseController {

    @Resource
    private ActualPriceService actualPriceService;

    @PostMapping("/query")
    public BaseResponse list(@RequestBody ActualPriceVO actualPriceVO){
        PageInfo<ActualPrice> actualPricePageInfo =  actualPriceService.selectWithPage(actualPriceVO);
        return super.successResult(actualPricePageInfo);
    }
}

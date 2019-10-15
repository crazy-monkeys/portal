package com.crazy.portal.controller.price;

import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.price.EnquiryPriceVO;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.price.EnquiryPrice;
import com.crazy.portal.service.price.EnquiryPriceService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @Desc: 询价
 * @Author: Bill
 * @Date: created in 15:45 2019-08-18
 * @Modified by:
 */
@RestController
@RequestMapping("/price/enquiry")
public class EnquiryController extends BaseController {


    @Resource
    private EnquiryPriceService enquiryPriceService;

    /**
     * 询价申请
     * @return
     */
    @PostMapping("/apply")
    @OperationLog
    public BaseResponse apply(@RequestBody EnquiryPriceVO enquiryPriceVO){
        enquiryPriceService.apply(enquiryPriceVO,super.getCurrentUser().getLoginName());
        return super.successResult();
    }

    /**
     * 申请列表查询
     * @return
     */
    @PostMapping("/query")
    public BaseResponse query(@RequestBody EnquiryPriceVO enquiryPriceVO){
        enquiryPriceVO.setCreateId(super.getCurrentUserId());
        enquiryPriceVO.setProposer(super.getCurrentUser().getLoginName());
        PageInfo<EnquiryPrice> enquiryPrices = enquiryPriceService.query(enquiryPriceVO);
        return super.successResult(enquiryPrices);
    }

    /**
     * 申请单删除
     * @return
     */
    @DeleteMapping("/delete/{applyId}")
    @OperationLog
    public BaseResponse delete(@PathVariable Integer applyId){
        enquiryPriceService.delete(applyId);
        return super.successResult();
    }
}

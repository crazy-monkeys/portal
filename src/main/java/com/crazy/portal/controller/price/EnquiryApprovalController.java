package com.crazy.portal.controller.price;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.price.EnquiryApprovalBean;
import com.crazy.portal.bean.price.EnquiryPriceVO;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.price.EnquiryPrice;
import com.crazy.portal.service.price.EnquiryPriceService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Desc: 询价审批
 * @Author: Bill
 * @Date: created in 15:45 2019-08-18
 * @Modified by:
 */
@RestController
@RequestMapping("/price/enquiryApproval")
@Slf4j
public class EnquiryApprovalController extends BaseController {

    @Resource
    private EnquiryPriceService enquiryPriceService;

    /**
     * 审批
     * @param
     * @return
     */
    @PostMapping("/approval")
    public BaseResponse approval(@RequestBody EnquiryApprovalBean enquiryApprovalBean){
        enquiryApprovalBean.setApprover(super.getCurrentUser().getLoginName());
        enquiryPriceService.approve(enquiryApprovalBean);
        return super.successResult();
    }

    /**
     * 审批列表查询
     * @param enquiryPriceVO
     * @return
     */
    @PostMapping("/query")
    public BaseResponse query(@RequestBody EnquiryPriceVO enquiryPriceVO){

        PageInfo<EnquiryPrice> enquiryPrices = enquiryPriceService.query(enquiryPriceVO);
        return super.successResult(enquiryPrices);
    }
}

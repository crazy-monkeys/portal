package com.crazy.portal.controller.price;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.price.EnquiryApprovalBean;
import com.crazy.portal.bean.price.EnquiryPriceVO;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.price.EnquiryPrice;
import com.crazy.portal.service.price.EnquiryPriceService;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Desc: 询价审批
 * @Author: Bill
 * @Date: created in 15:45 2019-08-18
 * @Modified by:
 */
@RestController
@RequestMapping("/price/enquiryApproval")
public class EnquiryApprovalController extends BaseController {

    @Resource
    private EnquiryPriceService enquiryPriceService;

    /**
     * 审批
     * @param
     * @return
     */
    @PostMapping("/approval")
    public BaseResponse approval(@RequestBody List<EnquiryApprovalBean> enquiryApprovalBeans){

        enquiryApprovalBeans.forEach(x->enquiryPriceService.approve(x));
        return super.successResult();
    }

    /**
     * 审批列表查询
     * @param enquiryPriceVO
     * @return
     */
    @PostMapping("/query")
    public BaseResponse query(@RequestBody EnquiryPriceVO enquiryPriceVO){

        Page<EnquiryPrice> enquiryPrices = enquiryPriceService.query(enquiryPriceVO);
        return super.successResult(enquiryPrices);
    }
}

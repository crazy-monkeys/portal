package com.crazy.portal.controller.price;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.price.EnquiryApprovalBean;
import com.crazy.portal.bean.price.EnquiryPriceVO;
import com.crazy.portal.controller.BaseController;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 审批
     * @param
     * @return
     */
    @PostMapping("/approval")
    public BaseResponse approval(@RequestBody List<EnquiryApprovalBean> enquiryApprovalBeans){

        return super.successResult();
    }

    /**
     * 审批列表查询
     * @param enquiryPriceVO
     * @return
     */
    @PostMapping("/query")
    public BaseResponse query(@RequestBody EnquiryPriceVO enquiryPriceVO){

        return super.successResult();
    }

    /**
     * 申请详情
     * @param applyId
     * @return
     */
    @GetMapping("/detail/{applyId}")
    public BaseResponse detail(@PathVariable Integer applyId){

        //TODO 需要根据申请id的产品关联出商品的详情
        return super.successResult();
    }

}

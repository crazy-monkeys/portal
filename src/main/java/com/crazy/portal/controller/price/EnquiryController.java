package com.crazy.portal.controller.price;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc: 询价
 * @Author: Bill
 * @Date: created in 15:45 2019-08-18
 * @Modified by:
 */
@RestController
@RequestMapping("/price/enquiry")
public class EnquiryController extends BaseController {

    /**
     * 询价申请
     * @return
     */
    @PostMapping("/apply")
    public BaseResponse apply(){

        return super.successResult();
    }

    /**
     * 申请列表查询
     * @return
     */
    @PostMapping("/query")
    public BaseResponse query(){

        return super.successResult();
    }

    /**
     * 申请单删除
     * @return
     */
    @DeleteMapping("/delete")
    public BaseResponse delete(){

        return super.successResult();
    }
}

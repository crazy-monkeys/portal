package com.crazy.portal.controller.handover;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.handover.DeliverService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lee on 2019/8/9.
 */

@RestController
public class HandoverController extends BaseController {

    @Resource
    private DeliverService deliverService;

    /**
     * 出货数据查询
     * @param dealerName
     * @param status
     * @param deliveryStartDate
     * @param deliveryEndDate
     * @param uploadStartTime
     * @param uploadEndTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/deliver")
    public BaseResponse getPageList(String dealerName, Integer status,
                                    String deliveryStartDate, String deliveryEndDate,
                                    String uploadStartTime, String uploadEndTime,
                                    Integer pageNum, Integer pageSize) {
        return super.successResult(deliverService.getPageList(dealerName, status, deliveryStartDate, deliveryEndDate,
                uploadStartTime, uploadEndTime, pageNum, pageSize));
    }

    /**
     * 出货数据详情
     * @param id
     * @return
     */
    @GetMapping(value = "/deliver/{id}")
    public BaseResponse getDetailInfo(@PathVariable Integer id, Integer pageNum, Integer pageSize) {
        return super.successResult(deliverService.getDetailInfo(id, pageNum, pageSize));
    }

    /**
     * 出货数据审核（运作部审核）
     * @return
     */
    @GetMapping(value = "/deliver/approval/{id}")
    public BaseResponse approvalDeliverInfo(@PathVariable Integer id) {
        deliverService.approvalDeliverInfo(id, getCurrentUser().getId());
        return super.successResult();
    }
}

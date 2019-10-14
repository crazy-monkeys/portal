package com.crazy.portal.controller.order;

import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.order.DeliveryApproveVO;
import com.crazy.portal.bean.order.OrderApprovalBean;
import com.crazy.portal.bean.order.OrderQueryBean;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.order.OrderApplyService;
import com.crazy.portal.service.order.OrderApproveService;
import com.crazy.portal.service.order.OrderService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 订单审批
 * @uthor Shawn
 * @Date 2019-09-14
 */
@RestController
@RequestMapping("/order/approval")
public class OrderApprovalController extends BaseController {

    @Resource
    private OrderService orderService;
    @Resource
    private OrderApproveService orderApproveService;
    @Resource
    private OrderApplyService orderApplyService;

    /**
     * 审批列表
     * @param bean
     * @return
     */
    @PostMapping("/list")
    public BaseResponse list(@RequestBody OrderQueryBean bean){
        return successResult(orderApplyService.list(bean));
    }


    /**
     * 审批
     * @param bean
     * @return
     */
    @OperationLog
    @PostMapping("/approval")
    public BaseResponse approval(@RequestBody @Valid OrderApprovalBean bean) throws Exception {
        orderApproveService.approval(bean, super.getCurrentUser());
        return successResult();
    }

    /**
     * 提货单审批
     * @param deliveryApproveVO
     * @return
     */
    @PostMapping("/delivery")
    @OperationLog
    public BaseResponse approvalDelivery(@RequestBody DeliveryApproveVO deliveryApproveVO){
        orderService.deliveryApprove(deliveryApproveVO, getCurrentUserId());
        return successResult();
    }
}

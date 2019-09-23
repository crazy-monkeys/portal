package com.crazy.portal.controller.order;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.order.DeliveryApproveVO;
import com.crazy.portal.bean.order.OrderApprovalBean;
import com.crazy.portal.bean.order.OrderQueryBean;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.order.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.ParseException;

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

    /**
     * 审批列表
     * @param bean
     * @return
     */
    @PostMapping("/list")
    public BaseResponse list(@RequestBody OrderQueryBean bean){
        return successResult(orderService.list(bean));
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public BaseResponse detail(@PathVariable Integer id){
        return successResult(orderService.detail(id));
    }

    /**
     * 审批
     * @param bean
     * @return
     */
    @PostMapping("/approval")
    public BaseResponse approval(@RequestBody @Valid OrderApprovalBean bean) throws ParseException {
        orderService.approval(bean, super.getCurrentUser());
        return successResult();
    }

    /**
     * 提货单审批
     * @param deliveryApproveVO
     * @return
     */
    @PostMapping("/approval/delivery")
    public BaseResponse approvalDelivery(@RequestBody DeliveryApproveVO deliveryApproveVO){
        orderService.deliveryApprove(deliveryApproveVO, getCurrentUserId());
        return successResult();
    }
}

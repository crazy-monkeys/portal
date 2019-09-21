package com.crazy.portal.controller.order;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.order.OrderApprovalBean;
import com.crazy.portal.bean.order.OrderQueryBean;
import com.crazy.portal.controller.BaseController;
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
    public BaseResponse approval(@RequestBody @Valid OrderApprovalBean bean){
        orderService.approval(bean, getCurrentUserId());
        return successResult();
    }

    /**
     * 信贷信息
     * @param id
     * @return
     */
    @GetMapping("/creditInfo/{id}")
    public BaseResponse creditInfoQuery(@PathVariable Integer id){
        return successResult(orderService.creditInfoQuery(id));
    }
}

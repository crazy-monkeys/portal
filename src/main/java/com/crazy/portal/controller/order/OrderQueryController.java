package com.crazy.portal.controller.order;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.order.DeliveryOrderQueryVO;
import com.crazy.portal.bean.order.OrderQueryBean;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.order.OrderService;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.Enums;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.StringUtil;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 订单查询
 * @uthor Shawn
 * @Date 2019-09-14
 */
@RestController
@RequestMapping("/order/query")
public class OrderQueryController extends BaseController {

    @Resource
    private OrderService orderService;


    /**
     * 订单列表
     * @param bean
     * @return
     */
    @PostMapping("/list")
    public BaseResponse list(@RequestBody OrderQueryBean bean) {
        if(super.getCurrentUser().getUserType().equals(Enums.USER_TYPE.agent.toString())){
            bean.setDealerId(super.getCurrentUser().getDealerId());
        }
        return successResult(orderService.list(bean));
    }

    /**
     * 订单详情
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public BaseResponse detail(@PathVariable Integer id) {
        return successResult(orderService.detail(id));
    }

    /**
     *查询提货单
     */
    @PostMapping("/list/delivery")
    public BaseResponse deliveryList(@RequestBody DeliveryOrderQueryVO vo){
        if(super.getCurrentUser().getUserType().equals(Enums.USER_TYPE.agent.toString())){
            vo.setDealerId(super.getCurrentUser().getDealerId());
        }
        return successResult(orderService.deliveryOrderList(vo));
    }

    /**
     * 提货单审批查询
     * @param vo
     * @return
     */
    @PostMapping("/list/delivery/approval")
    public BaseResponse deliveryApprovalList(@RequestBody DeliveryOrderQueryVO vo){
        return successResult(orderService.deliveryOrderApprovalList(vo,getCurrentUserId()));
    }

    /**
     * 查询提货单审批明细
     * @param id
     * @return
     */
    @GetMapping("/delivery/approval/detail/{id}")
    public BaseResponse deliveryApprovalDetail(@PathVariable String id){
        BusinessUtil.assertFlase(StringUtil.isEmpty(id), ErrorCodes.BusinessEnum.ORDER_ID_NOT_FOUND);
        return successResult(orderService.deliveryApprovalDetail(Integer.valueOf(id)));
    }

    /**
     * 查询提货单明细
     * @param id
     * @return
     */
    @GetMapping("/delivery/detail/{id}")
    public BaseResponse deliveryDetail(@PathVariable String id){
        BusinessUtil.assertFlase(StringUtil.isEmpty(id), ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);
        return successResult(orderService.deliveryDetail(Integer.valueOf(id)));
    }
}

package com.crazy.portal.controller.order;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.order.DeliveryChangeVO;
import com.crazy.portal.bean.order.DeliveryOrderQueryVO;
import com.crazy.portal.bean.order.OrderQueryBean;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.order.OrderService;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.StringUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
     * 变更交期
     * @return
     * @throws Exception
     */
    @PostMapping("/modifyDeliveryDate")
    public BaseResponse modifyDeliveryDate(@RequestBody List<DeliveryChangeVO> changeVOS){
        orderService.modifyDeliveryDate(changeVOS, getCurrentUserId());
        return successResult();
    }

    /**
     * 取消订单
     * @param bean
     * @return
     */
    @PostMapping("/cancel")
    public BaseResponse cancel(@RequestBody DeliveryChangeVO bean) {
        orderService.cancel(bean, getCurrentUserId());
        return successResult();
    }

    /**
     *查询提货单
     */
    @PostMapping("/list/delivery")
    public BaseResponse deliveryList(@RequestBody DeliveryOrderQueryVO vo){
        return successResult(orderService.deliveryOrderList(vo));
    }

    /**
     * 查询提货单明细
     * @param id
     * @return
     */
    @GetMapping("/delivery/detail/{id}")
    public BaseResponse deliveryDetail(@PathVariable String id){
        BusinessUtil.assertFlase(StringUtil.isEmpty(id), ErrorCodes.BusinessEnum.ORDER_ID_NOT_FOUND);
        return successResult(orderService.deliveryDetail(Integer.valueOf(id)));
    }
}

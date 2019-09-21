package com.crazy.portal.controller.order;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.order.BatchModifyOrderBean;
import com.crazy.portal.bean.order.OrderQueryBean;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.order.OrderService;
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
    @GetMapping("/list")
    public BaseResponse list(OrderQueryBean bean) {
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
     * @param bean
     * @return
     * @throws Exception
     */
    @PostMapping("/modifyDeliveryDate")
    public BaseResponse modifyDeliveryDate(@RequestBody BatchModifyOrderBean bean) throws Exception {
        orderService.modifyDeliveryDate(bean, getCurrentUserId());
        return successResult();
    }

    /**
     * 取消订单
     * @param bean
     * @return
     */
    @PostMapping("/cancel")
    public BaseResponse cancel(@RequestBody BatchModifyOrderBean bean) {
        orderService.cancel(bean, getCurrentUserId());
        return successResult();
    }

    /**
     * 提货
     * @param bean
     * @return
     */
    @PostMapping("/takeGoods")
    public BaseResponse takeGoods(@RequestBody BatchModifyOrderBean bean) {
        orderService.takeGoods(bean, getCurrentUserId());
        return successResult();
    }
}

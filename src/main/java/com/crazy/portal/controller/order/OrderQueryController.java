package com.crazy.portal.controller.order;

import com.crazy.portal.bean.BaseResponse;
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
@RequestMapping("/orderQuery")
public class OrderQueryController extends BaseController {
    @Resource
    private OrderService orderService;

    @GetMapping("/list")
    public BaseResponse list(OrderQueryBean bean){
        return successResult(orderService.list(bean));
    }

    @GetMapping("/detail/{id}")
    public BaseResponse detail(@PathVariable Integer id){
        return successResult(orderService.detail(id));
    }

}

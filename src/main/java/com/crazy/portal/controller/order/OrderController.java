package com.crazy.portal.controller.order;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.order.OrderVO;
import com.crazy.portal.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created by weiying on 2019/7/29.
 */
@Slf4j
@RestController
@RequestMapping("/webservice")
public class OrderController extends BaseController{

    /**
     * ECC 订单状态更新test api
     * @param orderVO
     * @return
     */
    @PostMapping("/invoke")
    public BaseResponse updateOrderStatus(@RequestBody OrderVO orderVO){
        log.info("order param"+ JSON.toJSONString(orderVO));
        return successResult();
    }
}

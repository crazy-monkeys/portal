package com.crazy.portal.controller.webservice;

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
public class EntranceController extends BaseController{

    @GetMapping(value="/index")
    @ResponseBody
    public String test(){

        return "success";
    }

    @SuppressWarnings({ "rawtypes", "unchecked"})
   // @PostMapping(value="/invoke.html")


    /**
     * ECC 订单状态更新test api
     * @param orderVO
     * @return
     */
    @PostMapping("/invoke")
    public BaseResponse updateOrderStatus(@RequestBody OrderVO orderVO){
        log.info("webservice param"+ JSON.toJSONString(orderVO));
        return successResult();
    }
}

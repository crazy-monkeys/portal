package com.crazy.portal.controller.order;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.order.Order;
import com.crazy.portal.service.order.OrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 订单申请
 * @uthor Shawn
 * @Date 2019-09-14
 */
@RestController
@RequestMapping("/orderAapply")
public class OrderApplyController extends BaseController {

    @Resource
    private OrderService orderService;

    @PostMapping("/apply")
    public BaseResponse apply(@RequestBody @Valid Order order){
        orderService.apply(order, getCurrentUserId());
        return successResult();
    }

    @GetMapping("/templateDownload")
    public void download(HttpServletResponse response) throws Exception{
        orderService.templateDownload(response);
    }

    @PostMapping("/upload")
    public BaseResponse upload(MultipartFile file){
        return successResult(orderService.upload(file, this.getCurrentUser().getId()));
    }

}

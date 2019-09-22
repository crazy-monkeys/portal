package com.crazy.portal.controller.order;

import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.order.Order;
import com.crazy.portal.service.order.OrderApplyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 订单申请
 * @uthor Shawn
 * @Date 2019-09-14
 */
@RestController
@RequestMapping("/order/apply")
public class OrderApplyController extends BaseController {

    @Resource
    private OrderApplyService orderApplyService;

    /**
     * 提交申请
     * @param order
     * @return
     */
    @PostMapping("/submit")
    @OperationLog
    public BaseResponse submit(@RequestBody @Valid Order order){
        orderApplyService.submitApply(order, getCurrentUserId());
        return successResult();
    }

    /**
     * 模板下载
     * @param response
     * @throws Exception
     */
    @GetMapping("/lineTmpl")
    public void lineTmpl(HttpServletResponse response) throws Exception{
        orderApplyService.downloadLineTmpl(response);
    }

    /**
     * 上传订单行模板,做调价试算
     * @return
     */
    @PostMapping("/upload")
    @OperationLog
    public BaseResponse upload(@Valid Order order){

        return successResult(orderApplyService.parsingLineTmplFile(order));
    }

}

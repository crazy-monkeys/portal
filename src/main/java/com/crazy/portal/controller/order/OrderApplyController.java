package com.crazy.portal.controller.order;

import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.order.DeliveryOrderCancelVO;
import com.crazy.portal.bean.order.DeliveryOrderVO;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.order.DeliverOrder;
import com.crazy.portal.entity.order.OrderApply;
import com.crazy.portal.service.order.OrderApplyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Set;

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
     * 订单创建申请
     * @param orderApply
     * @return
     */
    @PostMapping("/create")
    @OperationLog
    public BaseResponse create(@RequestBody @Valid OrderApply orderApply){
        orderApplyService.createOrderApply(orderApply, getCurrentUserId());
        return successResult();
    }

    /**
     * 订单取消申请
     * @param itemIds
     * @return
     */
    @PostMapping("/cancel")
    @OperationLog
    public BaseResponse cancel(@RequestParam Set<Integer> itemIds) throws Exception{
        orderApplyService.cancelOrderApply(itemIds, getCurrentUserId());
        return successResult();
    }

    /**
     * 订单修改申请
     * @param itemIds
     * @return
     */
    @PostMapping("/modify")
    @OperationLog
    public BaseResponse modify(@RequestParam Set<Integer> itemIds) throws Exception{
        orderApplyService.cancelOrderApply(itemIds, getCurrentUserId());
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
    public BaseResponse upload(@Valid OrderApply orderApply){

        return successResult(orderApplyService.parsingLineTmplFile(orderApply));
    }

    /**
     * 提货申请
     * @param bean
     * @return
     */
    @PostMapping("/submitOrderDelivery")
    public BaseResponse submitOrderDelivery(@RequestBody DeliveryOrderVO bean) {
        orderApplyService.submitApplyDelivery(bean, getCurrentUserId());
        return successResult();
    }

    /**
     * 提货单修改
     * @param order
     * @return
     */
    @PostMapping("/update/delivery")
    public BaseResponse DeliveryUpdate(@RequestBody DeliverOrder order){
        orderApplyService.updateDeliveryOrder(order);
        return successResult();
    }

    /**
     * 提货单取消
     * @param order
     * @return
     */
    @PostMapping("/cancel/delivery")
    public BaseResponse DeliveryCancel(@RequestBody DeliveryOrderCancelVO order){
        orderApplyService.cancelDeliveryOrder(order);
        return successResult();
    }

    @GetMapping("/delete/delivery/{id}")
    public BaseResponse DeliveryDelete(@PathVariable Integer id){
        orderApplyService.deleteDeliveryOrder(id);
        return successResult();
    }
}

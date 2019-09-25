package com.crazy.portal.controller.order;

import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.order.DeliveryChangeVO;
import com.crazy.portal.bean.order.DeliveryOrderCancelVO;
import com.crazy.portal.bean.order.DeliveryOrderVO;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.order.DeliverOrder;
import com.crazy.portal.entity.order.Order;
import com.crazy.portal.entity.order.OrderApply;
import com.crazy.portal.service.order.OrderApplyService;
import com.crazy.portal.service.order.OrderService;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.ErrorCodes;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
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
    @Resource
    private OrderService orderService;

    /**
     * 订单创建申请
     * @param orderApply
     * @return
     */
    @OperationLog
    @PostMapping("/create")
    public BaseResponse create(@RequestBody @Valid OrderApply orderApply){
        orderApplyService.createOrderApply(orderApply, getCurrentUserId());
        return successResult();
    }

    /**
     * 订单修改申请
     * @param orderApply
     * @return
     */
    @OperationLog
    @PostMapping("/modify/{orderId}")
    public BaseResponse modify(@PathVariable Integer orderId,
                               @RequestBody @Valid OrderApply orderApply) throws Exception{


        this.checkOrder(orderId);

        orderApplyService.modifyOrderApply(orderApply,super.getCurrentUserId());
        return successResult();
    }

    /**
     * 订单取消申请
     * @param itemIds
     * @return
     */
    @OperationLog
    @PostMapping("/cancel/{orderId}")
    public BaseResponse cancel(@PathVariable Integer orderId,
                               @RequestParam Set<Integer> itemIds) throws Exception{

        this.checkOrder(orderId);

        orderApplyService.cancelOrderApply(itemIds, getCurrentUserId());
        return successResult();
    }

    /**
     * 变更交期
     * @return
     * @throws Exception
     */
    @OperationLog
    @PostMapping("/modifyDeliveryDate/{orderId}")
    public BaseResponse modifyDeliveryDate(@PathVariable Integer orderId,
                                           @RequestBody List<DeliveryChangeVO> changeVOS) throws Exception{

        this.checkOrder(orderId);

        orderApplyService.modifyDeliveryDateApply(orderId,changeVOS, getCurrentUserId());
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

    private void checkOrder(Integer orderId) {
        Order order = orderService.findOrder(orderId);
        BusinessUtil.notNull(order, ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);
        BusinessUtil.assertTrue(order.getActive().equals(1), ErrorCodes.BusinessEnum.ORDER_IS_FAILURE);
        BusinessUtil.assertTrue(order.getApprovalStatus().equals(1), ErrorCodes.BusinessEnum.ORDER_UN_APPROVE_PASS_ORDER);

        boolean isApprovalPendingOrder = orderApplyService.isApprovalPendingOrder(order.getRSapOrderId());
        BusinessUtil.assertFlase(isApprovalPendingOrder, ErrorCodes.BusinessEnum.ORDER_PENDING_ORDER);
    }
}

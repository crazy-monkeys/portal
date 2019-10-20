package com.crazy.portal.controller.order;

import com.crazy.portal.service.order.OrderApplyService;
import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.order.DeliveryChangeVO;
import com.crazy.portal.bean.order.DeliveryOrderCancelVO;
import com.crazy.portal.bean.order.DeliveryOrderVO;
import com.crazy.portal.bean.order.OrderQueryBean;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.order.DeliverOrder;
import com.crazy.portal.entity.order.OrderApply;
import com.crazy.portal.util.Enums;
import com.crazy.portal.service.order.OrderApplyService;
import com.crazy.portal.util.Enums;
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

    @PostMapping("/list")
    public BaseResponse list(@RequestBody OrderQueryBean orderQueryBean){
        if(super.getCurrentUser().getUserType().equals(Enums.USER_TYPE.agent.toString())){
            orderQueryBean.setCreateId(super.getCurrentUserId());
        }
        return successResult(orderApplyService.list(orderQueryBean));
    }

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
     * 审批驳回-订单修改申请
     * @param orderApply
     * @return
     */
    @OperationLog
    @PostMapping("/update")
    public BaseResponse update(@RequestBody @Valid OrderApply orderApply) throws Exception{

        orderApplyService.update(orderApply, getCurrentUserId());
        return successResult();
    }

    /**
     * 审批成功-订单修改申请
     * @param orderApply
     * @return
     */
    @OperationLog
    @PostMapping("/modify/{orderId}")
    public BaseResponse modify(@PathVariable Integer orderId,
                               @RequestBody @Valid OrderApply orderApply) throws Exception{


        orderApplyService.modifyOrderApply(orderId,orderApply,super.getCurrentUserId());
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

        orderApplyService.cancelOrderApply(orderId,itemIds, getCurrentUserId());
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

        orderApplyService.modifyDeliveryDateApply(orderId,changeVOS, getCurrentUserId());
        return successResult();
    }

    /**
     * 模板下载
     * @param response
     * @throws Exception
     */
//    @GetMapping("/lineTmpl")
    public void lineTmpl(HttpServletResponse response) throws Exception{
        orderApplyService.downloadLineTmpl(response);
    }

    /**
     * 上传订单行模板,做调价试算
     * @return
     */
    @PostMapping("/upload")
    public BaseResponse upload(@Valid OrderApply orderApply) throws Exception{
        return successResult(orderApplyService.parsingLineTmplFile(orderApply));
    }

    /**
     * 提货申请
     * @param bean
     * @return
     */
    @PostMapping("/submitOrderDelivery")
    @OperationLog
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
    @OperationLog
    public BaseResponse deliveryUpdate(@RequestBody DeliverOrder order){
        orderApplyService.updateDeliveryOrder(order, getCurrentUserId());
        return successResult();
    }

    /**
     * 提货单取消
     * @param order
     * @return
     */
    @PostMapping("/cancel/delivery")
    @OperationLog
    public BaseResponse deliveryCancel(@RequestBody DeliveryOrderCancelVO order){
        orderApplyService.cancelDeliveryOrder(order, getCurrentUserId());
        return successResult();
    }

    @GetMapping("/delete/delivery/{id}")
    public BaseResponse deliveryDelete(@PathVariable Integer id){
        orderApplyService.deleteDeliveryOrder(id);
        return successResult();
    }

    //收货
    @PostMapping("/delivery/receiving")
    public BaseResponse deliveryReceiving(@RequestBody DeliveryOrderVO bean){
        orderApplyService.receiving(bean, this.getCurrentUserId());
        return successResult();
    }
}

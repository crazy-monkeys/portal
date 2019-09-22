package com.crazy.portal.task.quartz;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.annotation.Task;
import com.crazy.portal.bean.order.wsdl.delivery.*;
import com.crazy.portal.dao.order.DeliverOrderMapper;
import com.crazy.portal.dao.order.OrderInvoiceMapper;
import com.crazy.portal.entity.order.DeliverOrder;
import com.crazy.portal.entity.order.OrderInvoice;
import com.crazy.portal.service.order.OrderApiService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: OrderInvoiceJob
 * @Author: God Man Qiu~
 * @Date: 2019/9/22 23:41
 */
@Task(value = "发票信息同步",scheduleCode = "order_invoice_sync")
@Slf4j
@DisallowConcurrentExecution
public class OrderInvoiceJob implements Job {
    @Resource
    private OrderInvoiceMapper orderInvoiceMapper;
    @Resource
    private DeliverOrderMapper deliverOrderMapper;
    @Resource
    private OrderApiService eccApiService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try{
            /**优先执行没有同步发票信息的提货单**/
            List<DeliverOrder> deliverOrders = deliverOrderMapper.selectOfInvoiceSync();


        }catch (Exception e){
            log.error("发票信息同步异常",e);
        }
    }
}

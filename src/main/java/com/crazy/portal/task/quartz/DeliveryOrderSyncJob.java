package com.crazy.portal.task.quartz;

import com.crazy.portal.annotation.Task;
import com.crazy.portal.bean.order.wsdl.delivery.*;
import com.crazy.portal.dao.order.DeliverOrderLineMapper;
import com.crazy.portal.dao.order.DeliverOrderMapper;
import com.crazy.portal.entity.order.DeliverOrder;
import com.crazy.portal.entity.order.DeliverOrderLine;
import com.crazy.portal.service.order.OrderApiService;
import com.crazy.portal.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName: DeliveryOrderSyncJob
 * @Author: God Man Qiu~
 * @Date: 2019/9/22 23:55
 */
@Task(value = "收货数据同步",scheduleCode = "delivery_order_sync")
@Slf4j
@DisallowConcurrentExecution
public class DeliveryOrderSyncJob implements Job {
    @Resource
    private OrderApiService eccApiService;
    @Resource
    private DeliverOrderMapper deliverOrderMapper;
    @Resource
    private DeliverOrderLineMapper deliverOrderLineMapper;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try{
            ZrfcsddeliverylistContent content = new ZrfcsddeliverylistContent();
            content.setChangedfrom(DateUtil.format(new Date(),DateUtil.WEB_FORMAT));
            content.setChangedto(DateUtil.format(DateUtil.computeWithMonth(new Date(),-1),DateUtil.WEB_FORMAT));
            Item item = new Item();
            content.setEtList(Arrays.asList(item));
            ZrfcsddeliverylistBody body = new ZrfcsddeliverylistBody(content);
            Zrfcsddeliverylist zrfcsddeliverylist = new Zrfcsddeliverylist(body);
            ZrfcsddeliverylistResponse response = eccApiService.deliveryList(zrfcsddeliverylist);

            if(null != response && null != response.getEtList() && null != response.getEtList().getItem()){
                response.getEtList().getItem().forEach(e->{
                    DeliverOrder deliverOrder = deliverOrderMapper.selectBySapDeliveryNo(e.getSapdeliveryid());
                    if(null != deliverOrder){
                        deliverOrder.setSoldTo(e.getSoldto());
                        deliverOrder.setSendTo(e.getSendto());
                        deliverOrder.setActualDeliveryDate(e.getActualdeliverydate());
                        deliverOrder.setUpdateTime(new Date());
                        deliverOrderMapper.updateByPrimaryKeySelective(deliverOrder);
                    }
                    DeliverOrderLine deliverOrderLine = deliverOrderLineMapper.selectBySapDeliveryOrderLineNo(e.getDeliveryitemno());
                    if(null != deliverOrderLine){
                        if(null != e.getDeliveryquantity()){
                            deliverOrderLine.setDeliveryQuantity(e.getDeliveryquantity().intValue());
                            deliverOrderLineMapper.updateByPrimaryKeySelective(deliverOrderLine);
                        }
                    }
                });
            }
        }catch (Exception e){
            log.error("收货数据同步异常！",e);
        }
    }
}

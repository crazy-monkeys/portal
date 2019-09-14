package com.crazy.portal.service.order;

import com.crazy.portal.bean.order.OrderApprovalBean;
import com.crazy.portal.bean.order.OrderCreditInfoBean;
import com.crazy.portal.bean.order.OrderQueryBean;
import com.crazy.portal.dao.order.OrderLineMapper;
import com.crazy.portal.dao.order.OrderMapper;
import com.crazy.portal.entity.order.Order;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.DateUtil;
import com.crazy.portal.util.Enums;
import com.crazy.portal.util.ErrorCodes;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单管理
 */
@Slf4j
@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderLineMapper orderLineMapper;

    /**
     * 订单列表查询
     * @param bean
     * @return
     */
    public PageInfo<Order> list(OrderQueryBean bean){
        PageHelper.startPage(bean.getPageIndex(), bean.getPageSize());
        List<Order> list = orderMapper.selectByPage(bean);
        return new PageInfo<>(list);
    }

    /**
     * 订单明细
     * @param id
     * @return
     */
    public Order detail(Integer id){
        BusinessUtil.notNull(id, ErrorCodes.BusinessEnum.ORDER_ID_IS_REQUIRED);
        Order order = orderMapper.selectByPrimaryKey(id);
        BusinessUtil.notNull(order, ErrorCodes.BusinessEnum.ORDER_INFO_NOT_FOUND);
        order.setLines(orderLineMapper.selectByOrderId(id));
        return order;
    }

    /**
     * 订单申请
     * @param order
     * @param userId
     */
    @Transactional
    public void apply(Order order, Integer userId){
        BusinessUtil.notNull(order, ErrorCodes.BusinessEnum.ORDER_INFO_IS_REQUIRED);
        BusinessUtil.notNull(order.getLines(), ErrorCodes.BusinessEnum.ORDER_LINES_IS_REQUIRED);
        order.setApprovalStatus(Enums.OrderApprovalStatus.WAIT_APPROVAL.getValue());
        order.setCreateId(userId);
        order.setCreateTime(DateUtil.getCurrentTS());
        orderMapper.insertSelective(order);
        order.getLines().forEach(line->{
            line.setOrderId(order.getId());
            line.setCreateId(userId);
            line.setCreateTime(DateUtil.getCurrentTS());
            orderLineMapper.insertSelective(line);
        });
    }

    /**
     * 变更交货日期
     */
    public void modifyDeliveryDate(Integer orderId, String deliveryDate, Integer userId) throws Exception{
        BusinessUtil.notNull(orderId, ErrorCodes.BusinessEnum.ORDER_ID_IS_REQUIRED);
        BusinessUtil.assertTrue(DateUtil.isValidDateFormat(deliveryDate, DateUtil.WEB_FORMAT), ErrorCodes.BusinessEnum.ORDER_DELIVERY_DATE_FORMAT_FAIL);
        Order order = new Order();
        order.setId(orderId);
        order.setDeliveryDate(DateUtil.parseDate(deliveryDate, DateUtil.WEB_FORMAT));
        order.setUpdateId(userId);
        order.setUpdateTime(DateUtil.getCurrentTS());
        orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 提货
     * @param orderId
     * @param userId
     */
    public void takeGoods(Integer orderId, Integer userId){

    }

    /**
     * 取消
     * @param orderId
     * @param userId
     */
    public void cancel(Integer orderId, Integer userId){

    }

    /**
     * 订单审批
     * @param bean
     */
    public void approval(OrderApprovalBean bean, Integer userId){
        Order order = orderMapper.selectByPrimaryKey(bean.getOrderId());
        BusinessUtil.notNull(order, ErrorCodes.BusinessEnum.ORDER_INFO_NOT_FOUND);
        order.setApprovalStatus(bean.getApprovalStatus());
        order.setRejectReason(bean.getRejectReason());
        order.setUpdateId(userId);
        order.setUpdateTime(DateUtil.getCurrentTS());
        orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 信用额度查询
     */
    public OrderCreditInfoBean creditInfoQuery(Integer orderId){
        OrderCreditInfoBean info = new OrderCreditInfoBean();
        //TODO
        return info;
    }
}

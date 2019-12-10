package com.crazy.portal.service.order;

import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.cusotmer.CustomerInfoMapper;
import com.crazy.portal.dao.product.ProductInfoDOMapper;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.entity.order.OrderLine;
import com.crazy.portal.entity.product.ProductInfoDO;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 23:47 2019-10-31
 * @Modified by:
 */
@Service
public class CommonOrderService {

    @Resource
    private ProductInfoDOMapper productInfoDOMapper;

    @Resource
    private CustomerInfoMapper customerInfoMapper;

    public void resetLines(List<OrderLine> lines) {
        lines.stream().forEach(x->{
            ProductInfoDO productInfoDO = this.getProductInfo(x.getProductId(),x.getPlatform());
            if(productInfoDO != null){
                x.setProduct(productInfoDO.getProduct());
                x.setBu(productInfoDO.getBu());
                x.setPdt(productInfoDO.getPdt());
            }
        });
    }


    public ProductInfoDO getProductInfo(String productId,String platform){
        ProductInfoDO productInfoDO = productInfoDOMapper.selectBySapMidAndPlatForm(productId,platform);
        return productInfoDO;
    }

    /**
     * 根据客户简称获取内部编码,默然为空
     * @param custAbbreviation
     * @return
     */
    public String getInCodeByAbbreviation(String custAbbreviation) {
        if(StringUtil.isNotEmpty(custAbbreviation)){
            CustomerInfo customerInfo = customerInfoMapper.selectInCustomerByAbb(custAbbreviation);
            if(customerInfo != null){
                //如果客户不为空传递内部编码,默认传空字符
                return customerInfo.getOutCode();
            }
        }
        return "";
    }

    /**
     * 检查行类是否重复-----物料+平台+客户确认唯一
     * @param lines
     * @return
     */
    protected void processRepeatedLine(List<OrderLine> lines){
        Map<String, List<OrderLine>> collect =
                lines.stream().collect(Collectors.groupingBy(line -> this.featchGroupBy(line)));

        collect.forEach((x,y)->{
            if(y.size() > 1){
                OrderLine orderLine = y.get(0);
                String msg = String.format(ErrorCodes.BusinessEnum.ORDER_NO_REPETITION.getZhMsg(),
                        orderLine.getProductId(),
                        orderLine.getPlatform(),
                        orderLine.getCustAbbreviation());

                throw new BusinessException(ErrorCodes.BusinessEnum.ORDER_NO_REPETITION.getCode(),msg);
            }
        });
    }

    /**
     * 检查价格是否为0
     * @param eccProductID
     * @param netprice
     * @param price
     */
    protected void checkPrice(String eccProductID, BigDecimal netprice, BigDecimal price) {
        boolean hasZeroPrice = Objects.isNull(price) || BigDecimal.ZERO.equals(price) ||
                Objects.isNull(netprice) || BigDecimal.ZERO.equals(netprice);

        if(hasZeroPrice){
            throw new BusinessException(ErrorCodes.BusinessEnum.ORDER_INVALID_PRODUCT.getCode(),
                    String.format(ErrorCodes.BusinessEnum.ORDER_INVALID_PRODUCT.getZhMsg(),eccProductID));
        }
    }


    private String featchGroupBy(OrderLine orderLine){
        return orderLine.getProductId() + orderLine.getPlatform()+orderLine.getCustAbbreviation();
    }
}

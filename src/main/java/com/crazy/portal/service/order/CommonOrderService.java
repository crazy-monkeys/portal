package com.crazy.portal.service.order;

import com.crazy.portal.dao.cusotmer.CustomerInfoMapper;
import com.crazy.portal.dao.product.ProductInfoDOMapper;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.entity.order.OrderLine;
import com.crazy.portal.entity.product.ProductInfoDO;
import com.crazy.portal.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}

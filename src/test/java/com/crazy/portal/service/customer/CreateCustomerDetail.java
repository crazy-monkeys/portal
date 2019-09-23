/*
package com.crazy.portal.service.customer;

import com.crazy.portal.bean.customer.wsdl.customer.detail.*;
import com.crazy.portal.bean.customer.wsdl.visits.VisitCreateHeader;
import com.crazy.portal.entity.cusotmer.CustomerProduct;
import com.crazy.portal.util.JaxbXmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

*/
/**
 * @ClassName: CreateCustomerDetail
 * @Author: God Man Qiu~
 * @Date: 2019/9/17 10:17
 *//*

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class CreateCustomerDetail {
    @Test
    public void test(){
        CustomerDetail detail = new CustomerDetail();
        detail.setCustomerID("B100606");

        getProduct(detail);
        getBusiness(detail);

        VisitCreateHeader header = new VisitCreateHeader();
        CustomerDetailContent content = new CustomerDetailContent(header, detail);
        CustomerDetailBody body = new CustomerDetailBody(content);
        CustomerDetailCreate create = new CustomerDetailCreate(body);
        try{
            String xml = JaxbXmlUtil.convertToXml(create);
            System.out.println(xml);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getProduct(CustomerDetail detail){
        CustomerProduct product = new CustomerProduct();
        product.setProduct("1234");
        product.setPNumberOne(new BigDecimal(1));
        product.setPNumberTwo(new BigDecimal(1));
        product.setPNumberThree(new BigDecimal(1));
        product.setPNumberFour(new BigDecimal(1));
        product.setPNumberFive(new BigDecimal(1));
        product.setPNumberSix(new BigDecimal(1));

        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductCode(product.getProduct());
        productInfo.setExpectedShipments(product.getPNumberOne().toString());

        detail.setProductInfo(productInfo);
    }

    public void getBusiness(CustomerDetail detail){
        AssetInfo assetInfo = new AssetInfo();
        assetInfo.setYear("2019");
        assetInfo.setSeason("01");
        assetInfo.setTotalAssets("1111");
        detail.setAssetInfo(assetInfo);
    }
}
*/

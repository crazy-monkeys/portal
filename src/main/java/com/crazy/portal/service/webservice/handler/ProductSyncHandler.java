package com.crazy.portal.service.webservice.handler;

import com.crazy.portal.bean.webservice.AbstractHandler;
import com.crazy.portal.bean.webservice.IHandler;
import com.crazy.portal.bean.webservice.request.ProductSyncRequest;
import com.crazy.portal.bean.webservice.response.ProductSyncResponse;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.service.product.ProductService;
import com.crazy.portal.util.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * @ClassName: ProductSyncHandler
 * @Author: God Man Qiu~
 * @Date: 2019/9/16 15:56
 */
@Slf4j
@Component(value = "portal.product.sync")
public class ProductSyncHandler extends AbstractHandler implements IHandler<ProductSyncRequest, ProductSyncResponse> {
    @Resource
    private ProductService productService;
    @Override
    public ProductSyncResponse process(ProductSyncRequest request) {
        ProductSyncResponse response = new ProductSyncResponse();
        try{
            productService.syncProduct();
        }catch (Exception e){
            log.error("产品信息同步异常",e);
            throw new BusinessException(ErrorCodes.BusinessEnum.PRODUCT_SYNC_ERROR);
        }
        response.success();
        return response;
    }
}

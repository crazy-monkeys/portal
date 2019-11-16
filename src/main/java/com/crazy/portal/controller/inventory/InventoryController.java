package com.crazy.portal.controller.inventory;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.inventory.InventoryRequest;
import com.crazy.portal.bean.inventory.InventoryResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.inventory.InventoryConversionDO;
import com.crazy.portal.entity.inventory.InventoryTransferDO;
import com.crazy.portal.service.inventory.InventoryService;
import com.crazy.portal.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 00:04 2019-11-15
 * @Modified by:
 */
@RestController
@Slf4j
@RequestMapping("/inventory")
public class InventoryController extends BaseController {

    @Value("${ecc.api.url}")
    private String ECC_API_URL;

    @Resource
    private InventoryService inventoryService;


    /**
     * 详情数据
     * @param summaryRequest
     * @return
     */
    @PostMapping("/detail")
    public BaseResponse detail(@RequestBody InventoryRequest summaryRequest){
        String url = String.format("%s%s%s",ECC_API_URL,"/http/BI/PORTAL/GETINVENTORYDETAIL",summaryRequest.toString());
        return getBaseResponse(url);
    }

    /**
     * 获取汇总数据
     * @return
     */
    @PostMapping("/summary")
    public BaseResponse summary(@RequestBody InventoryRequest summaryRequest){
        String url = String.format("%s%s%s",ECC_API_URL,"/http/BI/PORTAL/GETINVENTORYSUMMARY",summaryRequest.toString());
        return getBaseResponse(url);
    }

    /**
     * 转移
     * @param inventoryTransfers
     * @return
     */
    @PostMapping("/transfer")
    @OperationLog
    public BaseResponse transfer(@RequestBody List<InventoryTransferDO> inventoryTransfers){
        return super.successResult(inventoryService.transfer(super.getCurrentUser(),inventoryTransfers));
    }

    /**
     * 转换
     * @param conversionDOS
     * @return
     */
    @PostMapping("/conversion")
    @OperationLog
    public BaseResponse conversion(@RequestBody List<InventoryConversionDO> conversionDOS){
        return super.successResult(inventoryService.conversion(super.getCurrentUser(),conversionDOS));
    }

    private BaseResponse getBaseResponse(String url) {
        url = url.replaceAll(" ", "%20");
        log.info("inventory access url : " + url);
        try {
            String response = HttpClientUtils.get(url);
            response = response.replace("\\","");
            List<InventoryResponse> inventoryResponses =
                    JSON.parseArray(response.substring(1,response.length()-1), InventoryResponse.class);
            return super.successResult(inventoryResponses);
        } catch (Exception e) {
            log.error("", e);
            return super.failResult("第三方接口访问失败");
        }
    }
}

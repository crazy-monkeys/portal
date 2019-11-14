package com.crazy.portal.controller.inventory;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.inventory.InventoryRequest;
import com.crazy.portal.bean.inventory.InventoryResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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

    /**
     * 获取汇总数据
     * @return
     */
    @PostMapping("/detail")
    public BaseResponse detail(@RequestBody InventoryRequest summaryRequest){
        String url = String.format("%s%s%s",ECC_API_URL,"/http/BI/PORTAL/GETINVENTORYDETAIL",summaryRequest.toString());
        return getBaseResponse(url);
    }

    @PostMapping("/summary")
    public BaseResponse summary(@RequestBody InventoryRequest summaryRequest){
        String url = String.format("%s%s%s",ECC_API_URL,"/http/BI/PORTAL/GETINVENTORYSUMMARY",summaryRequest.toString());
        return getBaseResponse(url);
    }

    private BaseResponse getBaseResponse(String url) {
        log.info("inventory access url : " + url);
        try {
            String response = HttpClientUtils.get(url);
            log.info("inventory response: " + response);
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

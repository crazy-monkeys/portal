package com.crazy.portal.controller.inventory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.inventory.CustomerRequest;
import com.crazy.portal.bean.inventory.InventoryRequest;
import com.crazy.portal.bean.inventory.InventoryResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.dao.cusotmer.CustCorporateRelationshipMapper;
import com.crazy.portal.dao.cusotmer.CustomerInfoMapper;
import com.crazy.portal.entity.cusotmer.CustCorporateRelationship;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.entity.inventory.InventoryConversionDO;
import com.crazy.portal.entity.inventory.InventoryTransferDO;
import com.crazy.portal.service.inventory.InventoryService;
import com.crazy.portal.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @Resource
    private CustomerInfoMapper customerInfoMapper;
    @Resource
    private CustCorporateRelationshipMapper custCorporateRelationshipMapper;


    /**
     * 详情数据
     * @param summaryRequest
     * @return
     */
    @PostMapping("/detail")
    public BaseResponse detail(@RequestBody InventoryRequest summaryRequest){
        if(!super.getCurrentUser().getUserType().equals(Enums.USER_TYPE.internal.toString())){
            CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(super.getCurrentUser().getDealerId());
            summaryRequest.setSAgencyShortName(customerInfo.getCustAbbreviation());
        }
        String url = String.format("%s%s%s",ECC_API_URL,"/http/BI/PORTAL/GETINVENTORYDETAIL",summaryRequest.toString());
        return getBaseResponse(url);
    }

    /**
     * 获取汇总数据
     * @return
     */
    @PostMapping("/summary")
    public BaseResponse summary(@RequestBody InventoryRequest summaryRequest){
        if(!super.getCurrentUser().getUserType().equals(Enums.USER_TYPE.internal.toString())){
            CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(super.getCurrentUser().getDealerId());
            summaryRequest.setSAgencyShortName(customerInfo.getCustAbbreviation());
        }
        String url = String.format("%s%s%s",ECC_API_URL,"/http/BI/PORTAL/GETINVENTORYSUMMARY",summaryRequest.toString());
        return getBaseResponse(url);
    }

    /**
     * 获取客户专货库存
     * @return
     */
    @PostMapping("/customer")
    public BaseResponse customer(@RequestBody CustomerRequest customerRequest){
        if(!super.getCurrentUser().getUserType().equals(Enums.USER_TYPE.internal.toString())){
            CustCorporateRelationship rs = custCorporateRelationshipMapper.selectInCustomer(super.getCurrentUser().getDealerId());
            BusinessUtil.assertFlase(null == rs ,ErrorCodes.BusinessEnum.IN_CUSTOMER_IS_NULL);
            CustomerInfo inCustomerInfo = customerInfoMapper.selectByInCode(rs.getCorporateId());
            BusinessUtil.assertFlase(null == inCustomerInfo ,ErrorCodes.BusinessEnum.IN_CUSTOMER_IS_NULL);
            customerRequest.setSAgencyIncode(inCustomerInfo.getOutCode());
        }
        String url = String.format("%s%s%s",ECC_API_URL,"/http/BI/PORTAL/GET_CUSTOMER_INVENTORY_DATA",customerRequest.toString());
        return getCustResponse(url);
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
        url = url.replaceAll(" ", "%20").replace("+", "%2B").replace("/\\","%22").replace("/'", "%27").replace("/\\/","%2F");
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

    private BaseResponse getCustResponse(String url) {
        url = url.replaceAll(" ", "%20").replace("+", "%2B").replace("/\\","%22").replace("/'", "%27").replace("/\\/","%2F");
        log.info("inventory access url : " + url);
        Map<String, Object> rMap = new HashMap<>();
        try {
            String response = HttpClientUtils.get(url);
            JSONArray jsonArray = JSONArray.parseArray(response.substring(1,response.length()-1).replace("\\",""));
            List<String> res =getAllKey(jsonArray);

            rMap.put("header",res);
            rMap.put("body",response);
            return super.successResult(rMap);
        } catch (Exception e) {
            log.error("", e);
            return super.failResult("第三方接口访问失败");
        }
    }

    public static List<String> getAllKey(JSONArray jsonArray) {
        List<String> respnose = new ArrayList<>();
        List<String> rKeys = new ArrayList<>();
        if(jsonArray.size()>0){
            JSONObject object =  jsonArray.getJSONObject(0);
            Set set1 = object.keySet();
            Iterator it1 =  set1.iterator();
            while(it1.hasNext()){
                rKeys.add(String.valueOf(it1.next()));
            }
            respnose = rKeys.stream().filter(e -> e.contains("Y")).collect(Collectors.toList());
            Collections.sort(respnose);
        }
        return respnose;
    }
}

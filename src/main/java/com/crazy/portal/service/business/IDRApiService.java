package com.crazy.portal.service.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crazy.portal.bean.business.idr.IdrApprovalSubmitBean;
import com.crazy.portal.bean.business.idr.IdrApprovalSubmitResultBean;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.entity.business.idr.BusinessDiffPriceInfo;
import com.crazy.portal.entity.business.idr.BusinessIdrInfo;
import com.crazy.portal.entity.business.idr.BusinessInsuranceInfo;
import com.crazy.portal.entity.business.idr.BusinessReturnsInfo;
import com.crazy.portal.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Slf4j
@Service
public class IDRApiService {

    private static final String BPM_IDR_APPROVAL_URL = "/http/PORTAL/BPM/PROJECT_WEB_SUBMIT";
    /**
     * 提交保差退审批到BPM
     * @param requestBody
     * @return
     */
    public String portalSubmitApprovalToBPM(String requestBody) throws IOException {
        log.info("idr submit approval to bpm");
        log.info("requestBody:" + requestBody);
        String responseBody = HttpClientUtils.post(CallApiUtils.ECC_API_URL.concat(BPM_IDR_APPROVAL_URL), requestBody);
        log.info("responseBody:" + responseBody);
        return responseBody;
    }

    public Map<String, IdrApprovalSubmitResultBean> submitApprovalToBPM(BusinessIdrInfo bean) {
        Map<String, IdrApprovalSubmitResultBean> resultMap = new HashMap<>();
        try {
            Set<String> typeSet = getApprovalSubmitType(bean);
            for (String type : typeSet) {
                IdrApprovalSubmitBean submitBean = new IdrApprovalSubmitBean();
                submitBean.setType(type);
                submitBean.setShipperCode(bean.getShipperCode());
                submitBean.setCompany(bean.getCompany());
                submitBean.setCurrency(bean.getCurrency());
                submitBean.setInternalCustomer(bean.getInCustomerName());
                submitBean.setExternalCustomer(bean.getOutCustomerName());
                submitBean.setReason(bean.getReson());
                submitBean.setSumRemark(bean.getRemark());
                submitBean.setInsuredPriceItem(getInsuredProceItem(bean));
                submitBean.setRefundPriceItem(getRefundPriceItem(bean));
                submitBean.setReturnGoods(getReturnGoods(bean));
                submitBean.setExchangeGoods(getExchangeGoods(bean));
                JSONObject requestBodyJson = new JSONObject();
                requestBodyJson.put("data", submitBean);
                String requestBody = JSON.toJSONString(requestBodyJson);
                String responseBody = portalSubmitApprovalToBPM(requestBody);
                BusinessUtil.assertTrue(StringUtil.isNotBlank(responseBody), ErrorCodes.BusinessEnum.BUSINESS_IDR_SUBMIT_RESULT_IS_NULL);
                IdrApprovalSubmitResultBean resultBean = JSON.toJavaObject(JSON.parseObject(responseBody).getJSONObject("d"), IdrApprovalSubmitResultBean.class);
                if (resultBean.getResult().equals(Enums.BusinessIdrApprovalSubmitResult.FAILED.getCode())) {
                    throw new BusinessException(ErrorCodes.BusinessEnum.BUSINESS_IDR_SUBMIT_RESULT_FAIL.getCode(), resultBean.getMessage());
                }
                resultMap.put(type, resultBean);
            }
        }catch (ParseException ex){
            log.error(ErrorCodes.BusinessEnum.BUSINESS_IDR_APPROVAL_DATE_PARSE_EXCEPTION.getZhMsg(), ex);
            throw new BusinessException(ErrorCodes.BusinessEnum.BUSINESS_IDR_APPROVAL_DATE_PARSE_EXCEPTION);
        }catch (IOException ex){
            log.error(ErrorCodes.BusinessEnum.BUSINESS_IDR_APPROVAL_API_EXCEPTION.getZhMsg(), ex);
            throw new BusinessException(ErrorCodes.BusinessEnum.BUSINESS_IDR_APPROVAL_API_EXCEPTION);
        }
        return resultMap;
    }

    public Set<String> getApprovalSubmitType(BusinessIdrInfo bean){
        Set<String> set = new HashSet<>();
        if (bean.getType().equals(Enums.BusinessIdrType.INSURANCE.getCode())) {
            set.add(Enums.BusinessIdrApprovalSubmitType.KP.toString());
        }
        if(bean.getType().equals(Enums.BusinessIdrType.DIFF_PRICE.getCode())){
            set.add(Enums.BusinessIdrApprovalSubmitType.CP.toString());
        }
        if(bean.getType().equals(Enums.BusinessIdrType.RETURNS.getCode())){
            return isReturnOrChange(bean, set);
        }
        return set;
    }

    public Set<String> isReturnOrChange(BusinessIdrInfo bean, Set<String> set){
        bean.getRList().forEach(e->{
            if(e.getExchangeNum() != null && e.getExchangeNum() > 0){
                set.add(Enums.BusinessIdrApprovalSubmitType.HH.toString());
            }
            if(e.getReturnNum() != null && e.getReturnNum() > 0){
                set.add(Enums.BusinessIdrApprovalSubmitType.TH.toString());
            }
        });
        return set;
    }

    private List<IdrApprovalSubmitBean.ReturnGood> getReturnGoods(BusinessIdrInfo bean) {
        List<IdrApprovalSubmitBean.ReturnGood> returnGoods = new ArrayList<>();
        if(bean.getType().equals(Enums.BusinessIdrType.RETURNS.getCode())){
            for(BusinessReturnsInfo e : bean.getRList()){
                IdrApprovalSubmitBean.ReturnGood returnGood = new IdrApprovalSubmitBean.ReturnGood();
                returnGood.setReturnBU(e.getReturnBu());
                returnGood.setReturnPDT(e.getReturnPdt());
                returnGood.setReturnPlatform(e.getReturnPlatform());
                returnGood.setReturnProModel(e.getReturnProductModel());
                returnGood.setReturnQuantity(e.getReturnNum());
                returnGood.setReturnPrice(e.getReturnPrice() != null ? e.getReturnPrice().floatValue() : null);
                returnGood.setAgenceRate(Float.valueOf(e.getReturnAgencyRate()));
                returnGood.setReturnAmount(e.getReturnAmount() != null ? e.getReturnAmount().floatValue() : null);
                returnGood.setReturnRemark(e.getRemark());
                returnGoods.add(returnGood);
            }
        }
        return returnGoods;
    }

    private List<IdrApprovalSubmitBean.ExchangeGood> getExchangeGoods(BusinessIdrInfo bean) {
        List<IdrApprovalSubmitBean.ExchangeGood> exchangeGoods = new ArrayList<>();
        if(bean.getType().equals(Enums.BusinessIdrType.RETURNS.getCode())){
            for(BusinessReturnsInfo e : bean.getRList()){
                IdrApprovalSubmitBean.ExchangeGood exchangeGood = new IdrApprovalSubmitBean.ExchangeGood();
                exchangeGood.setExchangeBU(e.getExchangeBu());
                exchangeGood.setExchangePDT(e.getExchangePdt());
                exchangeGood.setExchangePlatform(e.getExchangePlatform());
                exchangeGood.setExchangeProModel(e.getExchangeProductModel());
                exchangeGood.setExchangeQuantity(e.getExchangeNum());
                exchangeGood.setExchangePrice(e.getExchangePrice() != null ? e.getExchangePrice().floatValue() : null);
                exchangeGood.setAgenceRate(null);
                exchangeGood.setExchangeAmount(e.getExchangeAmount() != null ? e.getExchangeAmount().floatValue() : null);
                exchangeGood.setExchangeRemark(e.getRemark());
                exchangeGoods.add(exchangeGood);
            }
        }
        return exchangeGoods;
    }

    private List<IdrApprovalSubmitBean.RefundPrice> getRefundPriceItem(BusinessIdrInfo bean) throws ParseException {
        List<IdrApprovalSubmitBean.RefundPrice> refundPriceItem = new ArrayList<>();
        if(bean.getType().equals(Enums.BusinessIdrType.DIFF_PRICE.getCode())){
            for(BusinessDiffPriceInfo e : bean.getDList()){
                IdrApprovalSubmitBean.RefundPrice refundPrice = new IdrApprovalSubmitBean.RefundPrice();
                refundPrice.setCustomer(e.getCustomerName());
                refundPrice.setBU(e.getBu());
                refundPrice.setPDT(e.getPdt());
                refundPrice.setProductType(e.getProductType());
                refundPrice.setPlatform(e.getPlatfom());
                refundPrice.setProductModel(e.getProductModel());
                refundPrice.setShipmentTime(e.getShipmentDate() != null ? DateUtil.parseDate(e.getShipmentDate(), DateUtil.WEB_FORMAT) : null);
                refundPrice.setQuantity(e.getNum());
                refundPrice.setCusPickPrice(e.getCustomerPrice() != null ? e.getCustomerPrice().floatValue() : null);
                refundPrice.setAgentPickPrice(e.getAgentPrice() != null ? e.getAgentPrice().floatValue() : null);
                //TODO 代理费率，接口获取
                refundPrice.setAgenceRate(null);
                refundPrice.setDifferencePrice(e.getDifferenceAmount().floatValue());
                refundPrice.setRefundRemark(e.getRemark());
                refundPriceItem.add(refundPrice);
            }
        }
        return refundPriceItem;
    }

    private List<IdrApprovalSubmitBean.InsuredPrice> getInsuredProceItem(BusinessIdrInfo bean) throws ParseException {
        List<IdrApprovalSubmitBean.InsuredPrice> insuredPriceItem = new ArrayList<>();
        if(bean.getType().equals(Enums.BusinessIdrType.INSURANCE.getCode())){
            for(BusinessInsuranceInfo e : bean.getIList()){
                IdrApprovalSubmitBean.InsuredPrice insuredPrice = new IdrApprovalSubmitBean.InsuredPrice();
                insuredPrice.setDeliveryTime(e.getReceiveGoodsDate() != null ? DateUtil.parseDate(e.getReceiveGoodsDate(), DateUtil.WEB_FORMAT) : null);
                insuredPrice.setAdjustPriceTime(e.getAdjustDate() != null ? DateUtil.parseDate(e.getAdjustDate(), DateUtil.WEB_FORMAT) : null);
                insuredPrice.setBU(e.getBu());
                insuredPrice.setPDT(e.getPdt());
                insuredPrice.setProductType(e.getProductType());
                insuredPrice.setPlatform(e.getPlatform());
                insuredPrice.setProductModel(e.getProductModel());
                insuredPrice.setInventoryQuantity(e.getNum() != null ? Integer.valueOf(e.getNum()) : null);
                insuredPrice.setCurrency(e.getCurrency());
                insuredPrice.setInventoryPrice(e.getPrice() != null ? Float.valueOf(e.getPrice()) : null);
                insuredPrice.setNewPrice(e.getNewPrice() != null ? Float.valueOf(e.getNewPrice()) : null);
                insuredPrice.setInsured(e.getInsuranceAmount() != null ? Float.valueOf(e.getInsuranceAmount()) : null);
                insuredPrice.setAdjustTime(e.getModifyDate() != null ? DateUtil.parseDate(e.getModifyDate(), DateUtil.WEB_FORMAT) : null);
                insuredPrice.setInsuredRemark(e.getRemark());
                insuredPriceItem.add(insuredPrice);
            }
        }
        return insuredPriceItem;
    }
}

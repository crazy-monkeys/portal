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

/**
 * 保差退接口
 * @Author Shawn
 * @Date 2019-08-16
 */
@Slf4j
@Service
public class IDRApiService {

    private static final String BPM_IDR_APPROVAL_URL = "/http/PORTAL/BPM/PROJECT_WEB_SUBMIT";

    /**
     * 提交保差退审批到BPM
     * @param bean
     * @return
     */
    public IdrApprovalSubmitResultBean submitApprovalToBPM(BusinessIdrInfo bean) {
        IdrApprovalSubmitResultBean resultBean = new IdrApprovalSubmitResultBean();
        try {
            IdrApprovalSubmitBean submitBean = new IdrApprovalSubmitBean();
            submitBean.setType(getApprovalSubmitType(bean));
            submitBean.setShipperCode(bean.getShipperCode());
            submitBean.setCompany(bean.getCompany());
            submitBean.setCurrency(bean.getCurrency());
            submitBean.setInternalCustomer(bean.getInCustomerName());
            submitBean.setExternalCustomer(bean.getOutCustomerName());
            submitBean.setReason(bean.getReson());
            submitBean.setSumRemark(bean.getRemark());
            submitBean.setInsuredPriceItem(getInsuredProceItem(bean.getIList()));
            submitBean.setRefundPriceItem(getRefundPriceItem(bean.getDList()));
            submitBean.setReturnGoods(getReturnGoods(bean.getRList()));
            JSONObject requestBodyJson = new JSONObject();
            requestBodyJson.put("data", submitBean);
            String requestBody = JSON.toJSONString(requestBodyJson);
            String responseBody = portalSubmitApprovalToBPM(requestBody);
            BusinessUtil.assertTrue(StringUtil.isNotBlank(responseBody), ErrorCodes.BusinessEnum.BUSINESS_IDR_SUBMIT_RESULT_IS_NULL);
            resultBean = JSON.toJavaObject(JSON.parseObject(responseBody).getJSONObject("d"), IdrApprovalSubmitResultBean.class);
            if (resultBean.getResult().equals(Enums.BusinessIdrApprovalSubmitResult.FAILED.getCode())) {
                throw new BusinessException(ErrorCodes.BusinessEnum.BUSINESS_IDR_SUBMIT_RESULT_IS_FAIL.getCode(), resultBean.getMessage());
            }
            resultBean.setType(submitBean.getType());
        }catch (ParseException ex){
            log.error(ErrorCodes.BusinessEnum.BUSINESS_IDR_APPROVAL_DATE_PARSE_EXCEPTION.getZhMsg(), ex);
            throw new BusinessException(ErrorCodes.BusinessEnum.BUSINESS_IDR_APPROVAL_DATE_PARSE_EXCEPTION);
        }catch (IOException ex){
            log.error(ErrorCodes.BusinessEnum.BUSINESS_IDR_APPROVAL_API_EXCEPTION.getZhMsg(), ex);
            throw new BusinessException(ErrorCodes.BusinessEnum.BUSINESS_IDR_APPROVAL_API_EXCEPTION);
        }
        return resultBean;
    }

    public String portalSubmitApprovalToBPM(String requestBody) throws IOException {
        log.info("idr submit approval to bpm");
        log.info("requestBody： {}", requestBody);
        String responseBody = HttpClientUtils.post(CallApiUtils.ECC_API_URL.concat(BPM_IDR_APPROVAL_URL), requestBody);
        log.info("responseBody：{}", responseBody);
        return responseBody;
    }

    /**
     * 获取提交审批类型
     * @param bean
     * @return
     */
    public String getApprovalSubmitType(BusinessIdrInfo bean){
        if (bean.getType().equals(Enums.BusinessIdrType.INSURANCE.getCode())) {
            return Enums.BusinessIdrApprovalSubmitType.KP.toString();
        }
        if(bean.getType().equals(Enums.BusinessIdrType.DIFF_PRICE.getCode())){
            return Enums.BusinessIdrApprovalSubmitType.CP.toString();
        }
        if(bean.getType().equals(Enums.BusinessIdrType.RETURNS.getCode())){
            return Enums.BusinessIdrApprovalSubmitType.TH.toString();
        }
        return StringUtil.EMPTY_STRING;
    }

    /**
     * 获取退货/换货数据
     * @param list
     * @return
     */
    private List<IdrApprovalSubmitBean.ReturnGood> getReturnGoods(List<BusinessReturnsInfo> list) {
        List<IdrApprovalSubmitBean.ReturnGood> returnGoods = new ArrayList<>();
        for(BusinessReturnsInfo e : list){
            IdrApprovalSubmitBean.ReturnGood returnGood = new IdrApprovalSubmitBean.ReturnGood();
            returnGood.setReturnBU(e.getReturnBu());
            returnGood.setReturnPDT(e.getReturnPdt());
            returnGood.setReturnPlatform(e.getReturnPlatform());
            returnGood.setReturnProModel(e.getReturnProductModel());
            returnGood.setReturnQuantity(e.getReturnNum());
            returnGood.setReturnPrice(e.getReturnPrice() != null ? e.getReturnPrice().floatValue() : null);
            returnGood.setAgenceRate(Float.valueOf(e.getReturnAgencyRate()));
            returnGood.setReturnAmount(e.getReturnAmount() != null ? e.getReturnAmount().floatValue() : null);

            returnGood.setExchangeBU(e.getExchangeBu());
            returnGood.setExchangePDT(e.getExchangePdt());
            returnGood.setExchangePlatform(e.getExchangePlatform());
            returnGood.setExchangeProModel(e.getExchangeProductModel());
            returnGood.setExchangeQuantity(e.getExchangeNum());
            returnGood.setExchangePrice(e.getExchangePrice() != null ? e.getExchangePrice().floatValue() : null);
            returnGood.setExchangeAmount(e.getExchangeAmount() != null ? e.getExchangeAmount().floatValue() : null);
            returnGood.setExchangeRemark(e.getRemark());
            returnGoods.add(returnGood);
        }
        return returnGoods;
    }

    /**
     * 获取差价数据
     * @param list
     * @return
     * @throws ParseException
     */
    private List<IdrApprovalSubmitBean.RefundPrice> getRefundPriceItem(List<BusinessDiffPriceInfo> list) throws ParseException {
        List<IdrApprovalSubmitBean.RefundPrice> refundPriceItem = new ArrayList<>();
        for(BusinessDiffPriceInfo e : list){
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
            refundPrice.setAgenceRate(Float.valueOf(e.getAgencyRate()));
            refundPrice.setDifferencePrice(e.getDifferenceAmount().floatValue());
            refundPrice.setRefundRemark(e.getRemark());
            refundPriceItem.add(refundPrice);
        }
        return refundPriceItem;
    }

    /**
     * 获取保价数据
     * @param list
     * @return
     * @throws ParseException
     */
    private List<IdrApprovalSubmitBean.InsuredPrice> getInsuredProceItem(List<BusinessInsuranceInfo> list) throws ParseException {
        List<IdrApprovalSubmitBean.InsuredPrice> insuredPriceItem = new ArrayList<>();
        for(BusinessInsuranceInfo e : list){
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
        return insuredPriceItem;
    }
}

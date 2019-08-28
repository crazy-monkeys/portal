package com.crazy.portal.service.business;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crazy.portal.bean.business.idr.BusinessFileUploadBean;
import com.crazy.portal.bean.business.idr.BusinessIdrQueryBean;
import com.crazy.portal.bean.business.idr.IdrApprovalSubmitBean;
import com.crazy.portal.bean.business.idr.IdrApprovalSubmitResultBean;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.bean.webservice.request.IDRApprovalRequest;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.business.idr.*;
import com.crazy.portal.entity.business.idr.*;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by bill on 2019/7/30.
 *  * I: insurance 保价
 *  * D: diff 差价
 *  * R: returns 退货
 */
@Slf4j
@Service
public class IDRService {

    @Resource
    private BusinessIdrInfoMapper businessIdrInfoMapper;
    @Resource
    private BusinessDiffPriceInfoMapper businessDiffPriceInfoMapper;
    @Resource
    private BusinessInsuranceInfoMapper businessInsuranceInfoMapper;
    @Resource
    private BusinessReturnsInfoMapper businessReturnsInfoMapper;
    @Resource
    private BusinessFileMapper businessFileMapper;
    @Resource
    private BusinessIdrApprovalMapper businessIdrApprovalMapper;

    @Value("${file.path.root}")
    private String filePath;

    private Multimap<Integer, String> fileCache = ArrayListMultimap.create();

    private static final String IDR_FILE_PATH = "business";
    /**
     * 保差退列表查询
     * @param bean
     * @return
     */
    public PageInfo<BusinessIdrInfo> selectByPage(BusinessIdrQueryBean bean){
        PortalUtil.defaultStartPage(bean.getPageIndex(), bean.getPageSize());
        List<BusinessIdrInfo> list = businessIdrInfoMapper.selectByPage(bean);
        return new PageInfo<>(list);
    }

    /**
     * 查询差保退信息详情
     * @param id
     * @return
     */
    public BusinessIdrInfo selectIdrDetail(Integer id){
        BusinessIdrInfo info = businessIdrInfoMapper.selectByPrimaryKey(id);
        BusinessUtil.notNull(info, ErrorCodes.BusinessEnum.BUSINESS_IDRINFO_IS_NULL);
        if(info.getType().equals(Enums.BusinessIdrType.INSURANCE.getCode())){
            info.setIList(businessInsuranceInfoMapper.selectByIdrInfoId(id));
        }else if(info.getType().equals(Enums.BusinessIdrType.DIFF_PRICE.getCode())){
            info.setDList(businessDiffPriceInfoMapper.selectByIdrInfoId(id));
        }else if(info.getType().equals(Enums.BusinessIdrType.RETURNS.getCode())){
            info.setRList(businessReturnsInfoMapper.selectByIdrInfoId(id));
        }
        info.setFiles(businessFileMapper.selectByIdrInfoId(id));
        return info;
    }

    /**
     * 模板下载
     * @param type 1.保价 2.差价补偿 3.退换货
     * @param response
     */
    public void templateDownload(Integer type, HttpServletResponse response) throws Exception{
        BusinessUtil.notNull(type, ErrorCodes.BusinessEnum.BUSINESS_TYPE_IS_NULL);
        Map<String, List<? extends BaseRowModel>> resultMap = new HashMap<>();
        Enums.BusinessIdrType enumType = Enums.BusinessIdrType.getDescByCode(type);
        String sheetName = enumType.getDesc();
        resultMap.put("sheet1", Collections.singletonList(enumType.getType()));
        ExcelUtils.createExcelStreamMutilByEaysExcel(response, resultMap, sheetName, ExcelTypeEnum.XLSX);
    }
    /**
     * 上传附件
     * @param id 保差退ID
     * @param type 1.保价 2.差价补偿 3.退换货
     * @param fileType 1：普通附件 2：保差退附件 3：财务完结附件
     * @param crAmount CR金额
     * @param file 文件
     * @return
     */
    public BusinessFileUploadBean upload(Integer id, Integer type, Integer fileType, BigDecimal crAmount, MultipartFile file, Integer userId) throws Exception{
        checkFileUploadParam(id, type, fileType, file);
        BusinessFileUploadBean result = new BusinessFileUploadBean();
        FileVO fileVo = FileUtil.upload(file, getIdrFilePath());
        if(fileType.equals(Enums.BusinessFileType.IDR.getCode())){
            Enums.BusinessIdrType idrType = Enums.BusinessIdrType.getDescByCode(type);
            List<BaseRowModel> records = ExcelUtils.readExcel(fileVo.getFullPath(), idrType.getType().getClass());
            result.setIdrList(records);
        }
        if(fileType.equals(Enums.BusinessFileType.FINANCIAL_CLOSURE.getCode())){
            financialClosure(id, fileType, crAmount, userId, fileVo);
        }
        result.setFileName(fileVo.getFileName());
        result.setFilePath(fileVo.getFullPath());
        fileCache.put(userId, fileVo.getFullPath());
        return result;
    }

    private void checkFileUploadParam(Integer id, Integer type, Integer fileType, MultipartFile file) {
        BusinessUtil.notNull(fileType, ErrorCodes.BusinessEnum.BUSINESS_FILE_TYPE_IS_NULL);
        BusinessUtil.notNull(file, ErrorCodes.BusinessEnum.BUSINESS_FILE_IS_NULL);
        if(fileType.equals(Enums.BusinessFileType.FINANCIAL_CLOSURE.getCode())){
            BusinessUtil.notNull(id, ErrorCodes.BusinessEnum.BUSINESS_IDR_ID_IS_NULL);
        }
        if(fileType.equals(Enums.BusinessFileType.IDR.getCode())){
            BusinessUtil.notNull(type, ErrorCodes.BusinessEnum.BUSINESS_TYPE_IS_NULL);
        }
    }

    private void financialClosure(Integer id, Integer fileType, BigDecimal crAmount, Integer userId, FileVO fileVo) {
        saveBusinessFile(id, fileType, userId, fileVo);
        updateIdrInfoStatus(id, crAmount, userId);
    }

    private void updateIdrInfoStatus(Integer id, BigDecimal crAmount, Integer userId) {
        BusinessIdrInfo info = businessIdrInfoMapper.selectByPrimaryKey(id);
        BusinessUtil.assertTrue(info.getStatus().equals(Enums.BusinessIdrStatus.APPROVAL_OVER.getCode()), ErrorCodes.BusinessEnum.BUSINESS_IDR_STATUS_NOT_APPROVAL);
        info.setId(id);
        info.setCrAmount(crAmount);
        info.setStatus(Enums.BusinessIdrStatus.FINISHED.getCode());
        info.setUpdateId(userId);
        info.setUpdateTime(DateUtil.getCurrentTS());
        businessIdrInfoMapper.updateByPrimaryKeySelective(info);
    }

    private void saveBusinessFile(Integer id, Integer fileType, Integer userId, FileVO fileVo) {
        BusinessFile businessFile = new BusinessFile();
        businessFile.setIdrInfoId(id);
        businessFile.setFileName(fileVo.getFileName());
        businessFile.setFilePath(fileVo.getFullPath());
        businessFile.setFileType(fileType);
        businessFile.setCreateId(userId);
        businessFile.setCreateTime(DateUtil.getCurrentTS());
        businessFileMapper.insertSelective(businessFile);
    }

    public String getIdrFilePath(){
        return filePath.concat(File.separator).concat(IDR_FILE_PATH).concat(File.separator);
    }

    public void save(BusinessIdrInfo bean, Integer userId) throws Exception{
        bean.setStatus(Enums.BusinessIdrStatus.APPROVAL_SUBMIT.getCode());
        bean.setCreateId(userId);
        bean.setCreateTime(DateUtil.getCurrentTS());
        businessIdrInfoMapper.insertSelective(bean);

        if(bean.getType().equals(Enums.BusinessIdrType.INSURANCE.getCode())) {
            saveExtendsInfo(bean.getIList(), businessInsuranceInfoMapper, bean);
        }
        if(bean.getType().equals(Enums.BusinessIdrType.DIFF_PRICE.getCode())) {
            saveExtendsInfo(bean.getDList(), businessDiffPriceInfoMapper, bean);
        }
        if(bean.getType().equals(Enums.BusinessIdrType.RETURNS.getCode())) {
            saveExtendsInfo(bean.getRList(), businessReturnsInfoMapper, bean);
        }
        saveExtendsInfo(bean.getFiles(), businessFileMapper, bean);

        clearDiscardFile(bean.getFiles(), userId);

//        submitApprovalToBPM(bean);
    }

    public void saveExtendsInfo(List<? extends IdrBaseEntity> list, IdrBaseMapper mapper, BusinessIdrInfo bean){
        if(list == null){
            return;
        }
        list.forEach(e->{
            e.setIdrInfoId(bean.getId());
            e.setCreateId(bean.getCreateId());
            e.setCreateTime(bean.getCreateTime());
            mapper.insertSelective(e);
        });
    }

    public void clearDiscardFile(List<BusinessFile> files, Integer userId){
        if(files == null){
            return;
        }
        List<String> filePaths = files.stream().map(BusinessFile::getFilePath).collect(Collectors.toList());
        Collection<String> caches = fileCache.get(userId);
        caches.stream().filter(e->!filePaths.contains(e)).forEach(e->new File(e).delete());
    }


    public void submitApprovalToBPM(BusinessIdrInfo bean) throws Exception{
        IdrApprovalSubmitBean submitBean = new IdrApprovalSubmitBean();
        submitBean.setType(getApprovalSubmitType(bean.getType()));
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
        JSONObject requestBodyJson = new JSONObject();
        requestBodyJson.put("data", submitBean);
        String requestBody = JSON.toJSONString(requestBodyJson);
        log.info("IDR审批申请提交至BPM");
        log.info("requestBody:" + requestBody);
        String responseBody = CallApiUtils.portalSubmitApprovalToBPM(requestBody);
        log.info("responseBody:" + responseBody);
        BusinessUtil.assertTrue(StringUtil.isNotBlank(responseBody), ErrorCodes.BusinessEnum.BUSINESS_IDR_SUBMIT_RESULT_IS_NULL);
        IdrApprovalSubmitResultBean resultBean = JSON.toJavaObject(JSON.parseObject(responseBody).getJSONObject("d"), IdrApprovalSubmitResultBean.class);
        if(resultBean.getResult().equals(Enums.BusinessIdrApprovalSubmitResult.FAILED.getCode())){
            throw new BusinessException(ErrorCodes.BusinessEnum.BUSINESS_IDR_SUBMIT_RESULT_FAIL.getCode(), resultBean.getMessage());
        }
        saveApprovalRecord(bean, submitBean, resultBean);
    }

    private List<IdrApprovalSubmitBean.ReturnGood> getReturnGoods(BusinessIdrInfo bean) {
        List<IdrApprovalSubmitBean.ReturnGood> returnGoods = new ArrayList<>();
        if(bean.getType().equals(Enums.BusinessIdrType.RETURNS.getCode())){
            for(BusinessReturnsInfo e : bean.getRList()){
                IdrApprovalSubmitBean.ReturnGood returnGood = new IdrApprovalSubmitBean.ReturnGood();
                returnGood.setReturnBU(e.getBu());
                returnGood.setReturnPDT(e.getPdt());
                returnGood.setReturnPlatform(e.getPlatform());
                returnGood.setReturnProModel(e.getProductModel());
                returnGood.setReturnQuantity(e.getNum());
                returnGood.setReturnPrice(e.getPrice() != null ? e.getPrice().floatValue() : null);
                //TODO 代理费率，接口获取
                returnGood.setAgenceRate(null);
                returnGood.setReturnAmount(e.getAmount() != null ? e.getAmount().floatValue() : null);
                returnGood.setReturnRemark(e.getRemark());
                returnGoods.add(returnGood);
            }
        }
        return returnGoods;
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
                refundPrice.setShipmentTime(e.getShipmentDate() != null ? DateUtil.parseDate(e.getShipmentDate(), DateUtil.NEW_FORMAT) : null);
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
                insuredPrice.setDeliveryTime(e.getReceiveGoodsDate() != null ? DateUtil.parseDate(e.getReceiveGoodsDate(), DateUtil.NEW_FORMAT) : null);
                insuredPrice.setAdjustPriceTime(e.getAdjustDate() != null ? DateUtil.parseDate(e.getAdjustDate(), DateUtil.NEW_FORMAT) : null);
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
                insuredPrice.setAdjustTime(e.getModifyDate() != null ? DateUtil.parseDate(e.getModifyDate(), DateUtil.NEW_FORMAT) : null);
                insuredPrice.setInsuredRemark(e.getRemark());
                insuredPriceItem.add(insuredPrice);
            }
        }
        return insuredPriceItem;
    }

    private void saveApprovalRecord(BusinessIdrInfo bean, IdrApprovalSubmitBean submitBean, IdrApprovalSubmitResultBean resultBean) {
        BusinessIdrApproval approvalRecord = new BusinessIdrApproval();
        approvalRecord.setIdrInfoId(bean.getId());
        approvalRecord.setOrderType(submitBean.getType());
        approvalRecord.setOrderNo(resultBean.getOrderNO());
        approvalRecord.setCurrentReviewer(resultBean.getReviewer());
        approvalRecord.setMessage(resultBean.getMessage());
        approvalRecord.setCreateTime(DateUtil.getCurrentTS());
        businessIdrApprovalMapper.insertSelective(approvalRecord);
    }

    public String getApprovalSubmitType(Integer type){
        if (type.equals(Enums.BusinessIdrType.INSURANCE.getCode())) {
            return Enums.BusinessIdrApprovalSubmitType.KP.toString();
        }
        if(type.equals(Enums.BusinessIdrType.DIFF_PRICE.getCode())){
            return Enums.BusinessIdrApprovalSubmitType.CP.toString();
        }
        if(type.equals(Enums.BusinessIdrType.RETURNS.getCode())){
            return Enums.BusinessIdrApprovalSubmitType.TH.toString();
        }
        return null;
    }

    public void receiveApproval(IDRApprovalRequest request) {
        BusinessIdrApproval histortRecord = businessIdrApprovalMapper.selectByOrderNo(request.getOrderNumber());
        BusinessUtil.notNull(histortRecord, ErrorCodes.BusinessEnum.BUSINESS_IDR_APPROVAL_ORDERNO_NOT_FOUND);
        saveIdrApprovalRecord(request, histortRecord);
        if(request.getReviewStatus().equals(Enums.BusinessIdrApprovalStatus.AGREE.getCode()) && StringUtil.isBlank(request.getCurrentReviewer())){
            updateIdrInfoByApproval(request.getApiUserId(), histortRecord.getIdrInfoId(), Enums.BusinessIdrStatus.APPROVAL_OVER.getCode());
        }
        if(request.getReviewStatus().equals(Enums.BusinessIdrApprovalStatus.REJECT.getCode())){
            updateIdrInfoByApproval(request.getApiUserId(), histortRecord.getIdrInfoId(), Enums.BusinessIdrStatus.REJECT.getCode());
        }
    }

    private void updateIdrInfoByApproval(String apiUserId, Integer IdrInfoId, Integer status) {
        BusinessIdrInfo idrInfo = businessIdrInfoMapper.selectByPrimaryKey(IdrInfoId);
        idrInfo.setUpdateId(Integer.valueOf(apiUserId));
        idrInfo.setUpdateTime(DateUtil.getCurrentTS());
        idrInfo.setStatus(status);
        businessIdrInfoMapper.updateByPrimaryKeySelective(idrInfo);
    }

    private void saveIdrApprovalRecord(IDRApprovalRequest request, BusinessIdrApproval histortRecord) {
        BusinessIdrApproval record = new BusinessIdrApproval();
        record.setIdrInfoId(histortRecord.getIdrInfoId());
        record.setOrderType(histortRecord.getOrderType());
        record.setOrderNo(histortRecord.getOrderNo());
        record.setCurrentReviewer(request.getCurrentReviewer());
        record.setReviewedPeople(request.getReviewedPeople());
        record.setComment(request.getComment());
        record.setReviewStatus(request.getReviewStatus());
        record.setCreateTime(DateUtil.getCurrentTS());
        businessIdrApprovalMapper.insertSelective(record);
    }
}

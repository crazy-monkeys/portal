package com.crazy.portal.service.business;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.crazy.portal.bean.business.idr.*;
import com.crazy.portal.bean.common.Constant;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.bean.webservice.request.IDRApprovalRequest;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.business.idr.*;
import com.crazy.portal.entity.business.idr.BusinessFile;
import com.crazy.portal.entity.business.idr.BusinessIdrApproval;
import com.crazy.portal.entity.business.idr.BusinessIdrInfo;
import com.crazy.portal.entity.business.idr.IdrBaseEntity;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
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
    @Resource
    private IDRApiService idrApiService;
    @Value("${file.path.root-local}")
    private String filePath;
    @Value("${file.path.reader-dir}")
    private String readerFilePath;

    private Multimap<Integer, String> FILE_CACHE = ArrayListMultimap.create();

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
        BusinessUtil.notNull(info, ErrorCodes.BusinessEnum.BUSINESS_IDR_INFO_IS_NOT_FOUND);
        if(info.getType().equals(Enums.BusinessIdrType.INSURANCE.getCode())){
            info.setIList(businessInsuranceInfoMapper.selectByIdrInfoId(id));
        }else if(info.getType().equals(Enums.BusinessIdrType.DIFF_PRICE.getCode())){
            info.setDList(businessDiffPriceInfoMapper.selectByIdrInfoId(id));
        }else if(info.getType().equals(Enums.BusinessIdrType.RETURNS.getCode())){
            info.setRList(businessReturnsInfoMapper.selectByIdrInfoId(id));
        }
        List<BusinessFile> files = businessFileMapper.selectByIdrInfoId(id);
        files.stream().forEach(file->file.setFilePath(readerFilePath.concat(file.getFileName())));
        info.setFiles(files);
        return info;
    }

    /**
     * 模板下载
     * @param idrType 保差退类型： 1.保价 2.差价补偿 3.退换货
     * @param response
     */
    public void templateDownload(Integer idrType, HttpServletResponse response) {
        try {
            BusinessUtil.notNull(idrType, ErrorCodes.BusinessEnum.BUSINESS_IDR_TYPE_IS_REQUIRED);
            Map<String, List> resultMap = new HashMap<>();
            Enums.BusinessIdrType enumType = Enums.BusinessIdrType.getDescByCode(idrType);
            resultMap.put(ExcelUtils.DEFAULT_SHEET_NAME, Collections.singletonList(enumType.getType()));
            ExcelUtils.createExcelStreamMutilByEaysExcel(response, resultMap, enumType.getDesc(), ExcelTypeEnum.XLSX);
        }catch (Exception ex){
            log.error(ErrorCodes.BusinessEnum.EXCEL_TEMPLATE_DOWNLOAD_FAIL.getZhMsg(), ex);
            throw new BusinessException(ErrorCodes.BusinessEnum.EXCEL_TEMPLATE_DOWNLOAD_FAIL);
        }
    }
    /**
     * 上传附件
     * @param bean
     * @return
     */
    @Transactional
    public BusinessFileUploadBean upload(IdrUploadBean bean, Integer userId){
        checkFileUploadParam(bean);
        BusinessFileUploadBean result = new BusinessFileUploadBean();
        FileVO fileVo = FileUtil.upload(bean.getFile(), getIdrFilePath());
        BusinessUtil.notNull(fileVo, ErrorCodes.BusinessEnum.BUSINESS_IDR_UPLOAD_FILE_IS_NULL);
        if(bean.getFileType().equals(Enums.BusinessFileType.IDR.getCode())){
            Enums.BusinessIdrType idrType = Enums.BusinessIdrType.getDescByCode(bean.getIdrType());
            List records = ExcelUtils.readExcel(fileVo.getFullPath(), idrType.getType().getClass());
            result.setIdrList(records);
        }
        if(bean.getFileType().equals(Enums.BusinessFileType.FINANCIAL_CLOSURE.getCode())){
            //上传财务完结文件
            financialClosure(bean, userId, fileVo);
        }
        result.setFileName(fileVo.getFileName());
        result.setFilePath(fileVo.getFullPath());
        result.setFileType(bean.getFileType());
        FILE_CACHE.put(userId, fileVo.getFullPath());
        return result;
    }

    /**
     * 校验上传参数
     * @param bean
     */
    private void checkFileUploadParam(IdrUploadBean bean) {
        BusinessUtil.notNull(bean.getFileType(), ErrorCodes.BusinessEnum.BUSINESS_IDR_FILE_TYPE_IS_REQUIRED);
        BusinessUtil.notNull(bean.getFile(), ErrorCodes.BusinessEnum.BUSINESS_IDR_FILE_IS_REQUIRED);
        if(bean.getFileType().equals(Enums.BusinessFileType.FINANCIAL_CLOSURE.getCode())){
            BusinessUtil.notNull(bean.getId(), ErrorCodes.BusinessEnum.BUSINESS_IDR_ID_IS_REQUIRED);
        }
        if(bean.getFileType().equals(Enums.BusinessFileType.IDR.getCode())){
            BusinessUtil.notNull(bean.getIdrType(), ErrorCodes.BusinessEnum.BUSINESS_IDR_TYPE_IS_REQUIRED);
        }
    }

    /**
     * 上传财务完结文件
     * @param bean
     * @param userId
     * @param fileVo
     */
    private void financialClosure(IdrUploadBean bean, Integer userId, FileVO fileVo) {
        saveBusinessFile(bean.getId(), bean.getFileType(), userId, fileVo);
        updateIdrInfoStatus(bean.getId(), bean.getCrAmount(), userId);
    }

    /**
     * 更新保差退记录状态
     * @param id
     * @param crAmount
     * @param userId
     */
    private void updateIdrInfoStatus(Integer id, BigDecimal crAmount, Integer userId) {
        BusinessIdrInfo info = businessIdrInfoMapper.selectByPrimaryKey(id);
        BusinessUtil.assertTrue(info.getStatus().equals(Enums.BusinessIdrStatus.AGREE.getCode()), ErrorCodes.BusinessEnum.BUSINESS_IDR_STATUS_NOT_APPROVAL);
        info.setId(id);
        info.setCrAmount(crAmount);
        info.setStatus(Enums.BusinessIdrStatus.FINISHED.getCode());
        info.setUpdateId(userId);
        info.setUpdateTime(DateUtil.getCurrentTS());
        businessIdrInfoMapper.updateByPrimaryKeySelective(info);
    }

    /**
     * 保存文件记录
     * @param id
     * @param fileType
     * @param userId
     * @param fileVo
     */
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

    /**
     * 提交
     * @param bean
     * @param userId
     */
    @SuppressWarnings("all")
    @Transactional
    public void submit(BusinessIdrInfo bean, Integer userId) {
        bean.setStatus(Enums.BusinessIdrStatus.APPROVAL_SUBMIT.getCode());
        bean.setCreateId(userId);
        bean.setCreateTime(DateUtil.getCurrentTS());
        businessIdrInfoMapper.insertSelective(bean);
        if(bean.getType().equals(Enums.BusinessIdrType.INSURANCE.getCode())) {
            BusinessUtil.assertFlase(null == bean.getCrAmount(),ErrorCodes.BusinessEnum.CRM_AMOUNT_ERROR);
            BusinessUtil.assertFlase(CollectionUtils.isEmpty(bean.getIList()), ErrorCodes.BusinessEnum.BUSINESS_IDR_SUBMIT_ILIST_IS_NULL);
            saveExtendsInfo(bean.getIList(), businessInsuranceInfoMapper, bean);
        }
        if(bean.getType().equals(Enums.BusinessIdrType.DIFF_PRICE.getCode())) {
            BusinessUtil.assertFlase(CollectionUtils.isEmpty(bean.getDList()), ErrorCodes.BusinessEnum.BUSINESS_IDR_SUBMIT_DLIST_IS_NULL);
            saveExtendsInfo(bean.getDList(), businessDiffPriceInfoMapper, bean);
        }
        if(bean.getType().equals(Enums.BusinessIdrType.RETURNS.getCode())) {
            BusinessUtil.assertFlase(null == bean.getCrAmount(),ErrorCodes.BusinessEnum.CRM_AMOUNT_ERROR);
            BusinessUtil.assertFlase(CollectionUtils.isEmpty(bean.getRList()), ErrorCodes.BusinessEnum.BUSINESS_IDR_SUBMIT_RLIST_IS_NULL);
            saveExtendsInfo(bean.getRList(), businessReturnsInfoMapper, bean);
        }
        saveExtendsInfo(bean.getFiles(), businessFileMapper, bean);
        saveApprovalRecord(bean, idrApiService.submitApprovalToBPM(bean));
        clearDiscardFile(bean.getFiles(), userId);
    }

    /**
     * 保存扩展信息
     * @param list
     * @param mapper
     * @param bean
     */
    public void saveExtendsInfo(List<? extends IdrBaseEntity> list, IdrBaseMapper mapper, BusinessIdrInfo bean){
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        list.forEach(e->{
            e.setIdrInfoId(bean.getId());
            e.setCreateId(bean.getCreateId());
            e.setCreateTime(bean.getCreateTime());
            mapper.insertSelective(e);
        });
    }

    /**
     * 清理上传的无用文件
     * @param files
     * @param userId
     */
    public void clearDiscardFile(List<BusinessFile> files, Integer userId){
        if(CollectionUtils.isEmpty(files)){
            return;
        }
        List<String> filePaths = files.stream().map(BusinessFile::getFilePath).collect(Collectors.toList());
        Collection<String> caches = FILE_CACHE.get(userId);
        caches.stream().filter(e->!filePaths.contains(e)).forEach(e->new File(e).delete());
    }

    /**
     * 保存申请记录
     * @param bean
     * @param resultBean
     */
    private void saveApprovalRecord(BusinessIdrInfo bean, IdrApprovalSubmitResultBean resultBean) {
        BusinessIdrApproval approvalRecord = new BusinessIdrApproval();
        approvalRecord.setIdrInfoId(bean.getId());
        approvalRecord.setOrderType(resultBean.getType());
        approvalRecord.setOrderNo(resultBean.getOrderNO());
        approvalRecord.setCurrentReviewer(resultBean.getReviewer());
        approvalRecord.setMessage(resultBean.getMessage());
        approvalRecord.setCreateTime(DateUtil.getCurrentTS());
        businessIdrApprovalMapper.insertSelective(approvalRecord);
    }

    /**
     * <b>接收审批结果</b>
     * <describe>
     * 退换货将发送两个审批请求，所以其中一条被驳回，那么整个请求被驳回，当所有审批被通过，整个请求被通过
     * </describe>
     * @param request
     */
    @SuppressWarnings("all")
    public void receiveApproval(IDRApprovalRequest request) {
        BusinessIdrApproval histortRecord = businessIdrApprovalMapper.selectByOrderNo(request.getOrderNumber());
        BusinessUtil.notNull(histortRecord, ErrorCodes.BusinessEnum.BUSINESS_IDR_APPROVAL_ORDER_NO_NOT_FOUND);
        saveIdrApprovalRecord(request, histortRecord);
        Integer status = null;
        List<BusinessIdrApproval> records = businessIdrApprovalMapper.selectByIdrInfoId(histortRecord.getIdrInfoId());
        BusinessUtil.assertFlase(CollectionUtils.isEmpty(records), ErrorCodes.BusinessEnum.BUSINESS_IDR_APPROVAL_RECORDS_IS_NULL);
        if(records.stream().anyMatch(e-> Enums.BusinessIdrApprovalStatus.REJECT.getCode().equalsIgnoreCase(e.getReviewStatus()))){
            //审批驳回
            status = Enums.BusinessIdrStatus.REJECT.getCode();
        }
        Set<String> orderNos = records.stream().map(BusinessIdrApproval::getOrderNo).collect(Collectors.toSet());
        Set<String> agreeSet = records.stream().filter(e-> Enums.BusinessIdrApprovalStatus.AGREE.getCode().equalsIgnoreCase(e.getReviewStatus()) && StringUtil.isBlank(e.getCurrentReviewer())).map(BusinessIdrApproval::getOrderNo).collect(Collectors.toSet());
        if(orderNos.size() == agreeSet.size()){
            //审批通过
            status = Enums.BusinessIdrStatus.AGREE.getCode();
        }
        if(status != null){
            //更新审批结果
            updateIdrInfoByApproval(request.getApiUserId(), histortRecord.getIdrInfoId(), status);
        }
    }

    /**
     * 更新审批结果
     * @param apiUserId
     * @param IdrInfoId
     * @param status
     */
    private void updateIdrInfoByApproval(String apiUserId, Integer IdrInfoId, Integer status) {
        BusinessIdrInfo idrInfo = businessIdrInfoMapper.selectByPrimaryKey(IdrInfoId);
        BusinessUtil.notNull(idrInfo, ErrorCodes.BusinessEnum.BUSINESS_IDR_APPROVAL_IDR_INFO_NOT_FOUND);
        idrInfo.setUpdateId(Integer.valueOf(apiUserId));
        idrInfo.setUpdateTime(DateUtil.getCurrentTS());
        idrInfo.setStatus(status);
        businessIdrInfoMapper.updateByPrimaryKeySelective(idrInfo);
    }

    /**
     * 保存审批记录
     * @param request
     * @param histortRecord
     */
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

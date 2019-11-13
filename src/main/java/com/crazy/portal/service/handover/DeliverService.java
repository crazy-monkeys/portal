package com.crazy.portal.service.handover;

import com.alibaba.fastjson.JSONObject;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.bean.handover.DeliverTemplateBean;
import com.crazy.portal.bean.handover.HandoverUploadVO;
import com.crazy.portal.bean.system.MailBean;
import com.crazy.portal.config.email.EmailHelper;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.handover.DeliverDetailMapper;
import com.crazy.portal.dao.handover.DeliverDetailUpdateMapper;
import com.crazy.portal.entity.cusotmer.CustomerContact;
import com.crazy.portal.entity.handover.DeliverDetail;
import com.crazy.portal.entity.handover.DeliverDetailUpdate;
import com.crazy.portal.entity.handover.DeliverReceiveRecord;
import com.crazy.portal.service.customer.CustCorporateRelationshipService;
import com.crazy.portal.service.customer.CustomerContactService;
import com.crazy.portal.service.customer.CustomerInfoService;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.crazy.portal.util.Enums.BI_FUNCTION_CODE.*;
import static com.crazy.portal.util.ErrorCodes.BusinessEnum.*;

/**
 * Created by lee on 2019/8/24.
 */

@Slf4j
@Service("deliver")
@Transactional
public class DeliverService extends AbstractHandover implements IHandover<DeliverDetail> {

    @Resource
    private HandoverService handoverService;
    @Resource
    private DeliverDetailMapper deliverDetailMapper;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private DeliverDetailUpdateMapper deliverDetailUpdateMapper;
    @Resource
    private EmailHelper emailHelper;
    @Resource
    private CustomerContactService customerContactService;

    @Value("${file.path.deliver.template}")
    private String deliverTemplatePath;
    @Value("${file.path.deliver.push}")
    private String deliverPushPath;
    @Value("${file.path.deliver.pull}")
    private String deliverPullPath;
    @Value("${file.path.deliver.local}")
    private String deliverLocalPath;
    @Value("${ftp.path.handover.push}")
    private String ftpPushPath;
    @Value("${ftp.path.handover.pull}")
    private String ftpPullPath;

    @Value("${portal.view-url}")
    private String portalViewUrl;


    @Override
    public HandoverUploadVO verificationData(List<DeliverDetail> deliverData, Integer userId) {
        if(null == deliverData || deliverData.isEmpty()){
            throw new BusinessException(HANDOVER_FILE_DATA_IS_NULL);
        }
        //数据包装，生成第三方需要的文件
        String thirdFileName = ExcelUtils.writeExcel(deliverPushPath, deliverData, DeliverDetail.class);
        //请求了第三方，并拿到了结果
        BiCheckResult checkResult = callBiServerByFtp(CHECK_SALES_IMPORT_FILE, deliverPushPath , thirdFileName, deliverPullPath);
        List<DeliverDetail> responseData = ExcelUtils.readExcel(checkResult.getFilePath(), DeliverDetail.class);
        //批次记录表
        DeliverReceiveRecord record = handoverService.genRecord(customerInfoService.getDealerByUser(userId).getCustName(), userId, 1);
        for(DeliverDetail detail : responseData){
            detail.setRecordId(record.getId());
            deliverDetailMapper.insertSelective(detail);
        }
        return genThirdResult(checkResult, responseData, record.getId());
    }

    @Override
    public HandoverUploadVO verificationDataByErrorData(List<?> data, Integer userId, Integer recordId) {
        if(null == data || data.isEmpty()){
            throw new BusinessException(HANDOVER_FILE_DATA_IS_NULL);
        }
        List<DeliverTemplateBean> errorList = (List<DeliverTemplateBean>) data;
        for(DeliverTemplateBean errorData : errorList) {
            DeliverDetail dbRecord = deliverDetailMapper.selectByPrimaryKey(Integer.parseInt(errorData.getErrorId()));
            //Excel内的ID未找到记录则不进行处理
            BusinessUtil.assertFlase(null == dbRecord || !dbRecord.getRecordId().equals(recordId), HANDOVER_DATA_NOT_EXISTS);
            DeliverDetail detail = JSONObject.parseObject(JSONObject.toJSONString(errorData), DeliverDetail.class);
            detail.setId(dbRecord.getId());
            detail.setErrorMsg(null);
            deliverDetailMapper.updateByPrimaryKeySelective(detail);
        }
        //复核数据记录是否还存在错误
//            int errorCnt = deliverDetailMapper.countErrorData(recordId);
//            BusinessUtil.assertFlase(errorCnt > 0, HANDOVER_DELIVER_EXISTS_DATA_ERROR);
        //重新生成数据
        List<DeliverDetail> fullData = deliverDetailMapper.selectByRecordId(recordId);
        String thirdFileName = ExcelUtils.writeExcel(deliverPushPath, fullData, DeliverDetail.class);
        BiCheckResult checkResult = callBiServerByFtp(CHECK_SALES_IMPORT_FILE, deliverPushPath , thirdFileName, deliverPullPath);
        List<DeliverDetail> responseData = ExcelUtils.readExcel(checkResult.getFilePath(), DeliverDetail.class);
        deliverDetailMapper.deleteByRecordId(recordId);
        for(DeliverDetail detail : responseData){
            detail.setRecordId(recordId);
            deliverDetailMapper.insertSelective(detail);
        }
        return genThirdResult(checkResult, responseData, recordId);
    }

    @Override
    public HandoverUploadVO saveData(Integer recordId, Integer userId) {
        int errorCnt = deliverDetailMapper.countErrorData(recordId);
        BusinessUtil.assertFlase(errorCnt > 0, HANDOVER_EXISTS_DATA_ERROR);
        List<DeliverDetail> deliverData = deliverDetailMapper.selectByRecordId(recordId);
        String thirdFileName = ExcelUtils.writeExcel(deliverPushPath, deliverData, DeliverDetail.class);
        BiCheckResult checkResult = callBiServerByFtp(SAVE_SALES_IMPORT_FILE, deliverPushPath, thirdFileName, deliverPullPath);
        if(checkResult.isSuccess()){
            handoverService.updateStatus(recordId, 2);
//            return null;
        }
        List<DeliverDetail> responseData = ExcelUtils.readExcel(checkResult.getFilePath(), DeliverDetail.class);
        deliverDetailMapper.deleteByRecordId(recordId);
        for(DeliverDetail detail : responseData){
            if(checkResult.isSuccess()){
                BusinessUtil.assertEmpty(detail.getThirdId(), HANDOVER_BI_ID_NOT_EMPTY);
            }
            detail.setRecordId(recordId);
            deliverDetailMapper.insertSelective(detail);
        }
        return genThirdResult(checkResult, responseData, recordId);
    }

    @Override
    public void downloadError(HttpServletResponse response, String fileName) {
        FileUtil.download(response, deliverPullPath, fileName);
    }

    @Override
    public PageInfo<DeliverDetail> getDetailList(Integer dealerId, Integer pageNum, Integer pageSize,
                                                 String uploadStartTime, String uploadEndTime,
                                                 String handoverStartTime, String handoverEndTime,
                                                 String customerFullName, String productModel, String deliveryType,
                                                 String orderMonth, String customerOrderNumber,
                                                 String warehouse, String deliveryCompany) {
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<DeliverDetail> result = deliverDetailMapper.selectByDealerId(dealerId,
                uploadStartTime, uploadEndTime, handoverStartTime, handoverEndTime,
                customerFullName, productModel, deliveryType, orderMonth, customerOrderNumber);
        return new PageInfo<>(result);
    }

    @Override
    public void downloadRejectData(Integer recordId, HttpServletResponse response) {
        List<DeliverDetail> result = deliverDetailMapper.selectByRecordId(recordId);
        String fileName = ExcelUtils.writeExcel(deliverLocalPath, result, DeliverDetail.class);
        FileUtil.download(response, deliverLocalPath, fileName);
    }

    @Override
    public PageInfo<DeliverDetail> getDetailInfo(Integer recordId, Integer pageNum, Integer pageSize) {
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<DeliverDetail> result = deliverDetailMapper.selectByRecordId(recordId);
        return new PageInfo<>(result);
    }

    @Override
    public void downloadTemplate(HttpServletResponse response) {
        FileUtil.download(response, deliverTemplatePath, "deliver_template.xlsx");
    }

    @Override
    public List<DeliverTemplateBean> uploadTemplateData(MultipartFile excel, Integer userId) {
        if(log.isDebugEnabled()){
            log.debug("Current user identity as [{}]", userId);
        }
        //判断当前用户是否为 代理商
        BusinessUtil.assertFlase(false, HANDOVER_NOT_DEALER);
        FileVO fileVo = FileUtil.upload(excel, deliverLocalPath);
        List<DeliverTemplateBean> deliverData =  ExcelUtils.readExcel(fileVo.getFullPath(), DeliverTemplateBean.class);
        Iterator<DeliverTemplateBean> iterator = deliverData.iterator();
        while (iterator.hasNext()) {
            DeliverTemplateBean templateBean = iterator.next();
            if(StringUtils.isEmpty(templateBean.getCustomerFullName())){
                iterator.remove();
                continue;
            }
            templateBean.setDealerName(customerInfoService.getDealerByUser(userId).getCustName());
        }
        return deliverData;
    }

    @Override
    public void batchDeleteData(Integer[] ids, Integer userId) {
        StringBuilder sb = new StringBuilder();
        for(Integer id : ids){
            DeliverDetail detail = deliverDetailMapper.selectByPrimaryKey(id);
            if(null == detail){
                throw new BusinessException(HANDOVER_DATA_NOT_EXISTS);
            }
            if(null == detail.getThirdId()){
                deliverDetailMapper.deleteByPrimaryKey(id);
                continue;
            }
            sb.append(detail.getThirdId());
            sb.append(",");
        }
        if(sb.length() == 0){
            return;
        }
        String custName = customerInfoService.getDealerByUser(userId).getCustName();
        Map<String, String> param = new HashMap<>();
        param.put("ID", sb.replace(sb.length() - 1, sb.length(), "").toString());
        param.put("UserName", custName);
        try {
            String response = CallApiUtils.callBiPostApi(DELETE_INVENTORY_CASE, "PORTAL/BI/", JSONObject.toJSONString(param));
            if(response.contains("删除成功")){
                deliverDetailMapper.batchDeleteByIds(ids);
            }else{
                throw new BusinessException(HANDOVER_DELETE_ERROR);
            }
        }catch (Exception ex) {
            throw new BusinessException(HANDOVER_BI_SERVER_EXCEPTION);
        }
    }

    @Override
    public void downloadDataByUpdate(HttpServletResponse response, Integer[] ids) {
        BusinessUtil.notNull(ids, HANDOVER_INVALID_PARAM);
        BusinessUtil.assertFlase(ids.length == 0, HANDOVER_INVALID_PARAM);
        List<DeliverDetail> deliverData = deliverDetailMapper.selectByIds(ids);
        ExcelUtils.writeExcel(response, deliverData, DeliverDetail.class);
    }

    public void updateDataToBi(Integer recordId) {
        List<DeliverDetail> deliverDetails = deliverDetailMapper.selectUpdateDataById(recordId);
        if(null == deliverDetails || deliverDetails.isEmpty()){
            throw new BusinessException(HANDOVER_DATA_NOT_EXISTS);
        }
        //数据包装，生成第三方需要的文件
        String thirdFileName = ExcelUtils.writeExcel(deliverPushPath, deliverDetails, DeliverDetail.class);
        List<DeliverDetail> responseData;
        try {
            //请求了第三方，并拿到了结果
            BiCheckResult checkResult = callBiServerByFtp(UPDATE_SALES_IMPORT_FILE, deliverPushPath , thirdFileName, deliverPullPath);
            responseData = ExcelUtils.readExcel(checkResult.getFilePath(), DeliverDetail.class);
            if(!checkResult.isSuccess()){
                for(DeliverDetail resData : responseData){
                    deliverDetailMapper.updateErrorInfoByBiId(resData.getThirdId(), resData.getErrorMsg());
                }
                throw new BusinessException(HANDOVER_UPDATE_ERROR);
            }else{
                for(DeliverDetail detail : responseData){
                    DeliverDetail dbRecord = deliverDetailMapper.selectByThirdId(detail.getThirdId());
                    if(null == dbRecord){
                        continue;
                    }
                    try {
                        BeanUtils.copyNotNullFields(detail, dbRecord);
                    }catch (Exception ex) {
                        throw new BusinessException("对象属性复制异常");
                    }
                    deliverDetailMapper.updateByPrimaryKeySelective(dbRecord);
                    deliverDetailUpdateMapper.batchDeleteByBiId(detail.getThirdId());
                }
            }
        }catch (Exception ex) {
            throw new BusinessException(HANDOVER_BI_SERVER_EXCEPTION);
        }
    }

    @Transactional
    @Override
    public void uploadDataByUpdate(MultipartFile excel, Integer userId) {
        Set<Integer> recordIds = new HashSet<>();
        List<String> biIds = new ArrayList<>();
        //
        List<DeliverDetailUpdate> deliverDetails = ExcelUtils.readExcel(excel, DeliverDetailUpdate.class);
        for(DeliverDetailUpdate detail : deliverDetails){
            biIds.add(String.valueOf(detail.getThirdId()));
            DeliverDetail dbRecord = deliverDetailMapper.selectByThirdId(detail.getThirdId());
            recordIds.add(null == dbRecord ? null : dbRecord.getRecordId());
        }
        //
        List<Integer> statusList = handoverService.getStatusByIds(recordIds);
        if(null == statusList || statusList.contains(4)){
            throw new BusinessException(HANDOVER_UPDATE_STATUS_ERROR);
        }
        deliverDetailUpdateMapper.batchInsertByBiId(biIds);
        handoverService.updateStatus(new ArrayList<>(recordIds), 4);
    }

    private void sendConfirmEmail(CustomerContact customerContact, DeliverDetail detail, List<DeliverDetail> detailList) {
        try {
            MailBean mailBean = new MailBean();
            mailBean.setTos(customerContact.getEmail());
            mailBean.setSubject("出货数据确认");
            mailBean.setTemplateName(EmailHelper.MAIL_TEMPLATE.DELIVER_DATA_CONFIRM.getTemplateName());
            String confirmLink = String.format("%s%s?detailIds=%s", portalViewUrl, "/deliver/confirm", detail.getIdStr());
            mailBean.setContent("请点击右侧链接进行出货数据确认：<a>" + confirmLink + "</a>");
            Map<String, Object> params = new HashMap<>();
            params.put("remind", "请点击链接进行出货数据确认");
            params.put("url", confirmLink);
            params.put("detailList", detailList);
            mailBean.setParams(params);
            emailHelper.sendHtmlMail(mailBean);
        }catch (Exception ex) {
            throw new BusinessException(HANDOVER_SEND_EMAIL_ERROR);
        }
    }

    private List<DeliverDetail> getDetailData(String idStr) {
        String[] idArray = idStr.split(",");
        List<DeliverDetail> detailList = new ArrayList<>();
        for(String id : idArray){
            DeliverDetail detail = deliverDetailMapper.selectByPrimaryKey(Integer.parseInt(id));
            detailList.add(detail);
        }
        return detailList;
    }

    public void sendConfirmEmail(Integer recordId) {
        BusinessUtil.notNull(recordId, HANDOVER_PARAM_TYPE_ERROR);
        List<DeliverDetail> deliverDetails = deliverDetailMapper.selectEmailInfoByRecordId(recordId);
        BusinessUtil.notNull(deliverDetails, HANDOVER_PARAM_TYPE_ERROR);
        for(DeliverDetail detail : deliverDetails){
            List<DeliverDetail> detailList = getDetailData(detail.getIdStr());
            try {
                List<CustomerContact> customerContacts = customerContactService.selectByCustName(detail.getCustomerFullName());
                BusinessUtil.notNull(customerContacts, HANDOVER_DATA_EMAIL_ERROR);
                for(CustomerContact customerContact : customerContacts){
                    if(!"C01".equals(customerContact.getType()) || StringUtils.isEmpty(customerContact.getEmail())){
                        continue;
                    }
                    sendConfirmEmail(customerContact, detail, detailList);
                }
            }catch (Exception ex) {
                throw new BusinessException(HANDOVER_DATA_EMAIL_ERROR);
            }
        }
    }

    public void confirmData(Integer[] detailIds, String confirmMsg, Integer confirmStatus) {
        deliverDetailMapper.updateConfirmMsgByIds(detailIds, confirmMsg, confirmStatus);
    }

    private HandoverUploadVO genThirdResult(BiCheckResult checkResult, List<DeliverDetail> responseData, Integer recordId) {
        HandoverUploadVO resultInfo = new HandoverUploadVO();
        resultInfo.setRecordId(recordId);
        resultInfo.setIsError(checkResult.isSuccess() ? 0 : 1);
        resultInfo.setDeliverDetails(responseData);
        if(!checkResult.isSuccess()){
            List<DeliverTemplateBean> errorData = deliverDetailMapper.selectErrorDataByRecord(recordId);
            String errorDataFileName = ExcelUtils.writeExcel(deliverPullPath, errorData, DeliverTemplateBean.class);
            resultInfo.setErrorFileName(errorDataFileName);
        }
        resultInfo.setMsg(checkResult.isSuccess() ? null : "请点击【下载错误数据】，并将更新后的数据重新上传");
        return resultInfo;
    }

    @Override
    protected String pushFtpPath() {
        return ftpPushPath;
    }

    @Override
    protected String pullLocalPath() {
        return ftpPullPath;
    }
}

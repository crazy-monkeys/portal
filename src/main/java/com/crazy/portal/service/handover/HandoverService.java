package com.crazy.portal.service.handover;

import com.alibaba.fastjson.JSONObject;
import com.crazy.portal.bean.handover.DeliverTemplateBean;
import com.crazy.portal.bean.handover.HandoverUploadVO;
import com.crazy.portal.bean.handover.ReceiveTemplateBean;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.handover.DeliverDetailMapper;
import com.crazy.portal.dao.handover.DeliverReceiveRecordMapper;
import com.crazy.portal.dao.handover.ReceiveDetailMapper;
import com.crazy.portal.entity.handover.DeliverDetail;
import com.crazy.portal.entity.handover.DeliverReceiveRecord;
import com.crazy.portal.entity.handover.ReceiveDetail;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.crazy.portal.util.ErrorCodes.BusinessEnum.*;
import static com.crazy.portal.util.Enums.*;
import static com.crazy.portal.util.Enums.BI_FUNCTION_CODE.*;
/**
 * Created by lee on 2019/8/10.
 */

@Slf4j
@Service
public class HandoverService {

    @Resource
    private DeliverReceiveRecordMapper deliverReceiveRecordMapper;
    @Resource
    private DeliverDetailMapper deliverDetailMapper;
    @Resource
    private ReceiveDetailMapper receiveDetailMapper;

    //出货配置信息
    @Value("${file.path.deliver.template}")
    private String deliverTemplatePath;
    @Value("${file.path.deliver.push}")
    private String deliverPushPath;
    @Value("${file.path.deliver.pull}")
    private String deliverPullPath;
    @Value("${file.path.deliver.local}")
    private String deliverLocalPath;
    //收货配置信息
    @Value("${file.path.receive.template}")
    private String receiveTemplatePath;
    @Value("${file.path.receive.push}")
    private String receivePushPath;
    @Value("${file.path.receive.pull}")
    private String receivePullPath;
    @Value("${file.path.receive.local}")
    private String receiveLocalPath;

    private String deliver_type = "deliver";
    private String receive_type = "receive";

    private String SUCCESS_COEE = "OK";
    private String ERROR_CODE = "NG";

    /**
     * 进出货记录分页列表
     * @param dealerName
     * @param status
     * @param uploadStartTime
     * @param uploadEndTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<DeliverReceiveRecord> getPageList(String dealerName, Integer status,
                                                      String uploadStartTime, String uploadEndTime,
                                                      Integer pageNum, Integer pageSize) {
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<DeliverReceiveRecord> result = deliverReceiveRecordMapper.selectPageInfo(dealerName, status,
                uploadStartTime, uploadEndTime, null, null);
        return new PageInfo<>(result);
    }

    public PageInfo<DeliverReceiveRecord> getRejectInfo(Integer dealerId, String type, Integer pageNum, Integer pageSize) {
        checkTypeValue(type);
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<DeliverReceiveRecord> result = deliverReceiveRecordMapper.selectPageInfo(null, 3,
                null, null, dealerId, deliver_type.equals(type) ? 1 : 2);
        return new PageInfo<>(result);
    }

    public PageInfo<?> getDetailList(String type, Integer dealerId, Integer pageNum, Integer pageSize) {
        checkTypeValue(type);
        PortalUtil.defaultStartPage(pageNum,pageSize);
        if(deliver_type.equals(type)){
            List<DeliverDetail> result = deliverDetailMapper.selectByDealerId(dealerId);
            return new PageInfo<>(result);
        }
        if(receive_type.equals(type)){
            List<ReceiveDetail> result = receiveDetailMapper.selectByDealerId(dealerId);
            return new PageInfo<>(result);
        }
        return null;
    }

    public void downloadRejectData(Integer recordId, String type, HttpServletResponse response) {
        checkTypeValue(type);
        if(deliver_type.equals(type)){
            List<DeliverDetail> result = deliverDetailMapper.selectByRecordId(recordId);
            String fileName = ExcelUtils.writeExcel(deliverLocalPath, result, DeliverDetail.class);
            FileUtil.download(response, deliverLocalPath, fileName);
        }
        if(receive_type.equals(type)){
            List<ReceiveDetail> result = receiveDetailMapper.selectByRecordId(recordId);
            String fileName = ExcelUtils.writeExcel(receiveLocalPath, result, ReceiveDetail.class);
            FileUtil.download(response, receiveLocalPath, fileName);
        }

    }

    /**
     * 进出货明细记录
     * @param recordId
     * @return
     */
    public PageInfo<?> getDetailInfo(Integer recordId, String type, Integer pageNum, Integer pageSize) {
        checkTypeValue(type);
        PortalUtil.defaultStartPage(pageNum,pageSize);
        if(deliver_type.equals(type)){
            List<DeliverDetail> result = deliverDetailMapper.selectByRecordId(recordId);
            return new PageInfo<>(result);
        }
        if(receive_type.equals(type)){
            List<ReceiveDetail> result = receiveDetailMapper.selectByRecordId(recordId);
            return new PageInfo<>(result);
        }
        return null;
    }

    @Transactional
    public void operationDeliverInfo(Integer id, Integer userId, String type, Integer status, String remark) {
        checkTypeValue(type);
//        if(deliver_type.equals(type)){
//
//        }
        DeliverReceiveRecord record = deliverReceiveRecordMapper.selectByPrimaryKey(id);
        BusinessUtil.assertFlase(null == record, HANDOVER_INVALID_PARAM);
        //执行驳回
        if(status == 3){
            BusinessUtil.assertFlase(record.getStatus() == 2, HANDOVER_NOT_REJECT);
        }
        //执行确认
        if(status == 1){
            BusinessUtil.assertFlase(record.getStatus() != -1, HANDOVER_NOT_CONFIRM);
        }
        if(false){
            //TODO 用户不是 销售运作部 不允许操作
        }
        record.setStatus(status);
        record.setRemark(remark);
        record.setApprovalUserId(userId);
        record.setApprovalTime(new Date());
        deliverReceiveRecordMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 下载模版
     * @param response
     * @param type
     */
    public void downloadTemplate(HttpServletResponse response, String type) {
        checkTypeValue(type);
        if(deliver_type.equals(type)){
            FileUtil.download(response, deliverTemplatePath, "deliver_template.xlsx");
        }
        if(receive_type.equals(type)){
            FileUtil.download(response, receiveTemplatePath, "receive_template.xlsx");
        }
    }

    /**
     * 解释文件内数据
     * @param excel
     * @param userId
     * @return
     */
    public List<?> uploadTemplateData(MultipartFile excel, Integer userId, String type) {
        checkTypeValue(type);
        if(log.isDebugEnabled()){
            log.debug("Current user identity as [{}]", userId);
        }
        //此处代码等业务确认之后进行替换（如何识别代理商身份）
        String dealerName = mockDealerInfo(userId).get("dealerName");
        //判断当前用户是否为 代理商
        BusinessUtil.assertFlase(false, HANDOVER_NOT_DEALER);
        if(deliver_type.equals(type)){
            List<DeliverTemplateBean> deliverData =  ExcelUtils.readExcel(excel, DeliverTemplateBean.class , 1);
            for(DeliverTemplateBean templateBean : deliverData){
                templateBean.setDealerName(dealerName);
            }
            return deliverData;
        }
        if(receive_type.equals(type)){
            List<ReceiveTemplateBean> receiveData =  ExcelUtils.readExcel(excel, ReceiveTemplateBean.class , 1);
            for(ReceiveTemplateBean templateBean : receiveData){
                templateBean.setDealerName(dealerName);
            }
            return receiveData;
        }
        return null;
    }

    private DeliverReceiveRecord genRecord(String dealerName, Integer userId, Integer type) {
        DeliverReceiveRecord record = new DeliverReceiveRecord();
        record.setUploadTime(new Date());
        record.setDealerName(dealerName);
        record.setDealerId(userId);
        record.setStatus(0);
        record.setType(type);
        record.setCreateTime(new Date());
        deliverReceiveRecordMapper.insertSelective(record);
        return record;
    }

    private HandoverUploadVO genThirdResult(BiCheckResult checkResult, List<?> responseData, Integer recordId, String type) {
        HandoverUploadVO resultInfo = new HandoverUploadVO();
        resultInfo.setRecordId(recordId);
        resultInfo.setIsError(checkResult.isSuccess() ? 0 : 1);
        if(deliver_type.equals(type)){
            resultInfo.setDeliverDetails(responseData);
            if(!checkResult.isSuccess()){
                List<DeliverTemplateBean> errorData = deliverDetailMapper.selectErrorDataByRecord(recordId);
                String errorDataFileName = ExcelUtils.writeExcel(deliverPullPath, errorData, DeliverTemplateBean.class);
                resultInfo.setErrorFileName(errorDataFileName);
            }
        }
        if(receive_type.equals(type)){
            resultInfo.setReceiveDetails(responseData);
            if(!checkResult.isSuccess()){
                List<ReceiveTemplateBean> errorData = receiveDetailMapper.selectErrorDataByRecord(recordId);
                String errorDataFileName = ExcelUtils.writeExcel(receivePullPath, errorData, ReceiveTemplateBean.class);
                resultInfo.setErrorFileName(errorDataFileName);
            }
        }
        resultInfo.setMsg(checkResult.isSuccess() ? null : "请点击【下载错误数据】，并将更新后的数据重新上传");
        return resultInfo;
    }

    @Transactional
    public HandoverUploadVO verificationDataByErrorData(List<?> data, Integer userId, String type, Integer recordId) {
        if(deliver_type.equals(type)){
            List<DeliverTemplateBean> errorList = (List<DeliverTemplateBean>) data;
            for(DeliverTemplateBean errorData : errorList) {
                DeliverDetail dbRecord = deliverDetailMapper.selectByPrimaryKey(errorData.getErrorId());
                //Excel内的ID未找到记录则不尽兴处理
                BusinessUtil.assertFlase(null == dbRecord || recordId != dbRecord.getRecordId(), HANDOVER_DATA_NOT_EXISTS);
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
            BiCheckResult checkResult = callBiServer(CHECK_SALES_IMPORT_FILE, (deliverPushPath+thirdFileName), deliverPullPath);
            List<DeliverTemplateBean> responseData = ExcelUtils.readExcel(checkResult.getFilePath(), DeliverTemplateBean.class);
            deliverDetailMapper.deleteByRecordId(recordId);
            for(DeliverTemplateBean templateBean : responseData){
                DeliverDetail detail = JSONObject.parseObject(JSONObject.toJSONString(templateBean), DeliverDetail.class);
                detail.setErrorMsg(templateBean.getThirdErrorMsg());
                detail.setRecordId(recordId);
                deliverDetailMapper.insertSelective(detail);
            }
            return genThirdResult(checkResult, responseData, recordId, type);
        }
        if(receive_type.equals(type)){
            List<ReceiveTemplateBean> errorList = (List<ReceiveTemplateBean>) data;
            for(ReceiveTemplateBean errorData : errorList){
                ReceiveDetail dbRecord = receiveDetailMapper.selectByPrimaryKey(errorData.getErrorId());
                //Excel内的ID未找到记录则不尽兴处理
                BusinessUtil.assertFlase(null == dbRecord || recordId != dbRecord.getRecordId(), HANDOVER_DATA_NOT_EXISTS);
                ReceiveDetail detail = JSONObject.parseObject(JSONObject.toJSONString(errorData), ReceiveDetail.class);
                detail.setId(dbRecord.getId());
                detail.setErrorMsg(null);
                receiveDetailMapper.updateByPrimaryKeySelective(detail);
            }

            List<ReceiveDetail> fullData = receiveDetailMapper.selectByRecordId(recordId);
            String thirdFileName = ExcelUtils.writeExcel(receivePushPath, fullData, ReceiveDetail.class);
            BiCheckResult checkResult = callBiServer(CHECK_INVENTORY_IMPORT_FILE, (receivePushPath+thirdFileName), receivePullPath);
            List<ReceiveTemplateBean> responseData = ExcelUtils.readExcel(checkResult.getFilePath(), ReceiveTemplateBean.class);
            deliverDetailMapper.deleteByRecordId(recordId);
            for(ReceiveTemplateBean templateBean : responseData){
                ReceiveDetail detail = JSONObject.parseObject(JSONObject.toJSONString(templateBean), ReceiveDetail.class);
                detail.setErrorMsg(templateBean.getThirdErrorMsg());
                detail.setRecordId(recordId);
                receiveDetailMapper.insertSelective(detail);
            }
            return genThirdResult(checkResult, responseData, recordId, type);
        }
        return null;
    }

    /**
     * 请求第三方校验数据结果
     * @param data
     * @param userId
     * @param type
     * @return
     */
    @Transactional
    public HandoverUploadVO verificationData(List<?> data, Integer userId, String type) {
        String dealerName = "模拟出来的代理商名字";
        if(deliver_type.equals(type)){
            List<DeliverDetail> deliverData = (List<DeliverDetail>) data;
            //数据包装，生成第三方需要的文件
            String thridFileName = ExcelUtils.writeExcel(deliverPushPath, deliverData, DeliverDetail.class);
            //请求了第三方，并拿到了结果
            BiCheckResult checkResult = callBiServer(CHECK_SALES_IMPORT_FILE, (deliverPushPath+thridFileName), deliverPullPath);
            List<DeliverTemplateBean> responseData = ExcelUtils.readExcel(checkResult.getFilePath(), DeliverTemplateBean.class);
            //批次记录表
            DeliverReceiveRecord record = genRecord(dealerName, userId, 1);
            for(DeliverTemplateBean templateBean : responseData){
                DeliverDetail detail = JSONObject.parseObject(JSONObject.toJSONString(templateBean), DeliverDetail.class);
                detail.setErrorMsg(templateBean.getThirdErrorMsg());
                detail.setRecordId(record.getId());
                deliverDetailMapper.insertSelective(detail);
            }
            return genThirdResult(checkResult, responseData, record.getId(), type);
        }
        if(receive_type.equals(type)){
            List<ReceiveDetail> receiveData = (List<ReceiveDetail>) data;
            String thridFileName = ExcelUtils.writeExcel(receivePushPath, receiveData, DeliverDetail.class);
            BiCheckResult checkResult = callBiServer(CHECK_INVENTORY_IMPORT_FILE, (receivePushPath+thridFileName), receivePullPath);
            List<ReceiveTemplateBean> responseData = ExcelUtils.readExcel(checkResult.getFilePath(), ReceiveTemplateBean.class);
            //批次记录表
            DeliverReceiveRecord record = genRecord(dealerName, userId, 2);
            for(ReceiveTemplateBean templateBean : responseData){
                ReceiveDetail detail = JSONObject.parseObject(JSONObject.toJSONString(templateBean), ReceiveDetail.class);
                detail.setErrorMsg(templateBean.getThirdErrorMsg());
                detail.setRecordId(record.getId());
                receiveDetailMapper.insertSelective(detail);
            }
            return genThirdResult(checkResult, responseData, record.getId(), type);
        }
        return null;
    }

    /**
     * 下载进出货错误数据（第三方提供文件）
     * @param response
     * @param fileName
     * @param type
     */
    public void downloadError(HttpServletResponse response, String fileName, String type) {
        checkTypeValue(type);
        if(deliver_type.equals(type)){
            FileUtil.download(response, deliverPullPath, fileName);
        }
        if(receive_type.equals(type)){
            FileUtil.download(response, receivePullPath, fileName);
        }
    }

    /**
     * 将校验通过的出货数据提交至第三方
     * @param id
     */
    public void submitData(Integer id, String type) {
        checkTypeValue(type);
        DeliverReceiveRecord record = deliverReceiveRecordMapper.selectByPrimaryKey(id);
        BusinessUtil.assertFlase(null == record, HANDOVER_INVALID_PARAM);
        BusinessUtil.assertFlase(record.getStatus() == -1, HANDOVER_WAITING_CONFIRM);
        BusinessUtil.assertFlase(record.getStatus() == 2, HANDOVER_ALREADY_CONFIRM);
        if(checkTimeline() && record.getStatus() != 1){
            record.setStatus(-1);//置为待确认状态
            deliverReceiveRecordMapper.updateByPrimaryKeySelective(record);
            throw new BusinessException(HANDOVER_WAITING_CONFIRM);
        }
        if(deliver_type.equals(type)){
            int errorCnt = deliverDetailMapper.countErrorData(id);
            BusinessUtil.assertFlase(errorCnt > 0, HANDOVER_EXISTS_DATA_ERROR);
            //TODO 生成文件 并 请求第三方提交数据接口
//        List<DeliverDetail> deliverData = null;
//        String newFileName = ExcelUtils.writeExcel(pushPath, deliverData, DeliverTemplateBean.class);
        }
        if(receive_type.equals(type)){
            int errorCnt = receiveDetailMapper.countErrorData(id);
            BusinessUtil.assertFlase(errorCnt > 0, HANDOVER_EXISTS_DATA_ERROR);
        }
        //提交给第三方 并更新状态
        record.setStatus(2);
        deliverReceiveRecordMapper.updateByPrimaryKeySelective(record);
    }

    private void checkTypeValue(String type) {
        boolean condition = !deliver_type.equals(type) && !receive_type.equals(type);
        BusinessUtil.assertFlase(condition, HANDOVER_PARAM_TYPE_ERROR);
    }

    private Map<String, String> mockDealerInfo(Integer userId) {
        Map<String, String> map = new HashMap<>();
        map.put("dealerName", "模拟的代理商");
        return map;
    }

    /**
     *
     * @param functionCode
     * @param pushPath
     * @param pullPath
     * @return
     */
    private BiCheckResult callBiServer(BI_FUNCTION_CODE functionCode, String pushPath, String pullPath) {
        try {
            String response = mockThridResult() ? "\"OK:/Users/lee/Documents/job_code/portal_file/pull_thrid/ok.xlsx\"" :
                    "\"NG:/Users/lee/Documents/job_code/portal_file/pull_thrid/error.xlsx\"";
//            String response = CallApiUtils.BITest(functionCode, pushPath, pullPath);
            if(StringUtils.isEmpty(response)){
                log.error("{} -> {}", HANDOVER_BI_RESPONSE_EXCEPTION.getZhMsg(), response);
                throw new BusinessException(HANDOVER_BI_RESPONSE_EXCEPTION);
            }
            if(log.isDebugEnabled()){
                log.debug("Bi server response info -> pushPath:[{}], pullPath:[{}], response:[{}]", pushPath, pullPath, response);
            }
            String fileName = response.split(":")[1];
            if(response.contains(SUCCESS_COEE)){
                return new BiCheckResult(true, fileName);
            }
            if(response.contains(ERROR_CODE)){
                return new BiCheckResult(false, fileName);
            }
            log.error(HANDOVER_BI_RESPONSE_EXCEPTION.getZhMsg(), response);
            throw new BusinessException(HANDOVER_BI_RESPONSE_EXCEPTION);

        }catch (Exception ex) {
            log.error(HANDOVER_BI_SERVER_EXCEPTION.getZhMsg(), ex);
            throw new BusinessException(HANDOVER_BI_SERVER_EXCEPTION);
        }
    }

    private boolean checkTimeline() {
        return mockThridResult();
    }

    /**
     * 模拟第三方结果
     * 截取时间戳最后一个数字，0 ～ 4：false 5 ～ 9：true
     * @return
     */
    private boolean mockThridResult() {
        String time = String.valueOf(new Date().getTime());
        int len = time.length();
        int i = Integer.parseInt(String.valueOf(time.charAt(len - 1)));
        return i > 4;
    }

    class BiCheckResult {

        //是否成功处理
        private boolean isSuccess = false;
        //文件地址（完整路径+文件名称）
        private String filePath;

        BiCheckResult() {
        }

        public BiCheckResult(boolean isSuccess, String filePath) {
            this.isSuccess = isSuccess;
            this.filePath = filePath;
        }

        public boolean isSuccess() {
            return isSuccess;
        }

        public String getFilePath() {
            return filePath;
        }
    }

}

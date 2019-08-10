package com.crazy.portal.service.handover;

import com.alibaba.fastjson.JSONObject;
import com.crazy.portal.bean.handover.DeliverTemplateBean;
import com.crazy.portal.bean.handover.HandoverUploadVO;
import com.crazy.portal.bean.handover.ReceiveTemplateBrean;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.handover.DeliverDetailMapper;
import com.crazy.portal.dao.handover.DeliverReceiveRecordMapper;
import com.crazy.portal.entity.handover.DeliverDetail;
import com.crazy.portal.entity.handover.DeliverReceiveRecord;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.ExcelUtils;
import com.crazy.portal.util.FileUtil;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
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

    //出货配置信息
    @Value("${file.path.deliver.template}")
    private String deliverTemplatePath;
    @Value("${file.path.deliver.push}")
    private String deliverPushPath;
    @Value("${file.path.deliver.pull}")
    private String deliverPullPath;
    //收货配置信息
    @Value("${file.path.receive.template}")
    private String receiveTemplatePath;
    @Value("${file.path.receive.push}")
    private String receivePushPath;
    @Value("${file.path.receive.pull}")
    private String receivePullPath;

    private String deliver_type = "deliver";
    private String receive_type = "receive";

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
                uploadStartTime, uploadEndTime, null);
        return new PageInfo<>(result);
    }

    public PageInfo<DeliverReceiveRecord> getDealerPageList(String dealerId, Integer pageNum, Integer pageSize) {
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<DeliverReceiveRecord> result = deliverReceiveRecordMapper.selectPageInfo(null, null, null, null, dealerId);
        return new PageInfo<>(result);
    }

    /**
     * 进出货明细记录
     * @param recordId
     * @return
     */
    public PageInfo<?> getDetailInfo(Integer recordId, String type, Integer pageNum, Integer pageSize) {
        PortalUtil.defaultStartPage(pageNum,pageSize);
        if(deliver_type.equals(type)){
            List<DeliverDetail> result = deliverDetailMapper.selectByRecordId(recordId);
            return new PageInfo<>(result);
        }
        return null;
    }

    @Transactional
    public void approvalDeliverInfo(Integer id, Integer userId, String type, Integer status, String remark) {
        checkTypeValue(type);
        if(deliver_type.equals(type)){
            DeliverReceiveRecord record = deliverReceiveRecordMapper.selectByPrimaryKey(id);
            BusinessUtil.assertFlase(null == record, HANDOVER_DELIVER_INVALID_PARAM);
            BusinessUtil.assertFlase(record.getStatus() != -1, HANDOVER_DELIVER_NOT_CONFIRM);
            if(false){
                //TODO 用户不是 销售运作部 不允许操作
            }
            record.setStatus(status);
            record.setRemark(remark);
            record.setApprovalUserId(userId);
            record.setApprovalTime(new Date());
            deliverReceiveRecordMapper.updateByPrimaryKeySelective(record);
        }
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
        BusinessUtil.assertFlase(false, HANDOVER_DELIVER_NOT_DEALER);
        if(deliver_type.equals(type)){
            List<DeliverTemplateBean> deliverData =  ExcelUtils.readExcel(excel, DeliverTemplateBean.class , 1);
            for(DeliverTemplateBean templateBean : deliverData){
                templateBean.setDealerName(dealerName);
            }
            return deliverData;
        }
        if(receive_type.equals(type)){
            List<ReceiveTemplateBrean> receiveData =  ExcelUtils.readExcel(excel, ReceiveTemplateBrean.class , 1);
            for(ReceiveTemplateBrean templateBean : receiveData){
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

    private HandoverUploadVO genThirdResult(Map<String, String> result, List<DeliverTemplateBean> responseData, Integer recordId) {
        HandoverUploadVO resultInfo = new HandoverUploadVO();
        boolean condition = "ok".equals(result.get("status"));
        resultInfo.setDeliverDetails(responseData);
        resultInfo.setRecordId(recordId);
        resultInfo.setIsError(condition ? 0 : 1);
        resultInfo.setDeliverDetails(responseData);
        if(!condition){
            List<DeliverTemplateBean> errorData = deliverDetailMapper.selectErrorDataByRecord(recordId);
            String errorDataFileName = ExcelUtils.writeExcel(deliverPushPath, errorData, DeliverTemplateBean.class);
            resultInfo.setErrorFileName(errorDataFileName);
        }
        resultInfo.setMsg(condition ? null : "请点击【下载错误数据】，并将更新后的数据重新上传");
        return resultInfo;
    }

    @Transactional
    public HandoverUploadVO verificationDataByErrorData(List<?> data, Integer userId, String type, Integer recordId) {
        if(deliver_type.equals(type)){
            List<DeliverTemplateBean> errorList = (List<DeliverTemplateBean>) data;
            for(DeliverTemplateBean errorData : errorList) {
                DeliverDetail dbRecord = deliverDetailMapper.selectByPrimaryKey(errorData.getErrorId());
                //Excel内的ID未找到记录则不尽兴处理
                BusinessUtil.assertFlase(null == dbRecord || recordId != dbRecord.getRecordId(), HANDOVER_DELIVER_DATA_NOT_EXISTS);
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
//            String thridFileName = ExcelUtils.writeExcel(deliverPushPath, fullData, DeliverDetail.class);
            Map<String, String> result = reqThridServer();
            List<DeliverTemplateBean> responseData = ExcelUtils.readExcel(deliverPullPath, result.get("fileName"), DeliverTemplateBean.class);
            deliverDetailMapper.deleteByRecordId(recordId);
            for(DeliverTemplateBean templateBean : responseData){
                DeliverDetail detail = JSONObject.parseObject(JSONObject.toJSONString(templateBean), DeliverDetail.class);
                detail.setErrorMsg(templateBean.getThirdErrorMsg());
                detail.setRecordId(recordId);
                deliverDetailMapper.insert(detail);
            }
            return genThirdResult(result, responseData, recordId);
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
        //需要给到第三方的文件
        String thridFileName = null;
        Map<String, String> result = null;
        String dealerName = "模拟出来的代理商名字";
        if(deliver_type.equals(type)){
            /*
            List<DeliverDetail> deliverData = (List<DeliverDetail>) data;
            //数据包装，生成第三方需要的文件
            thridFileName = ExcelUtils.writeExcel(deliverPushPath, deliverData, DeliverDetail.class);
            //假设请求了第三方，并拿到了结果
            */
            result = reqThridServer();
            List<DeliverTemplateBean> responseData = ExcelUtils.readExcel(deliverPullPath, result.get("fileName"), DeliverTemplateBean.class);
            //批次记录表
            DeliverReceiveRecord record = genRecord(dealerName, userId, 1);
            for(DeliverTemplateBean templateBean : responseData){
                DeliverDetail detail = JSONObject.parseObject(JSONObject.toJSONString(templateBean), DeliverDetail.class);
                detail.setErrorMsg(templateBean.getThirdErrorMsg());
                detail.setRecordId(record.getId());
                deliverDetailMapper.insert(detail);
            }
            return genThirdResult(result, responseData, record.getId());
        }
        if(receive_type.equals(type)){
            List<DeliverTemplateBean> receiveData = (List<DeliverTemplateBean>) data;
            thridFileName = ExcelUtils.writeExcel(receivePushPath, receiveData, ReceiveTemplateBrean.class);
            reqThridServer();
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
        if(deliver_type.equals(type)){
            DeliverReceiveRecord record = deliverReceiveRecordMapper.selectByPrimaryKey(id);
            BusinessUtil.assertFlase(null == record, HANDOVER_DELIVER_INVALID_PARAM);
            BusinessUtil.assertFlase(record.getStatus() == -1, HANDOVER_DELIVER_WAITING_CONFIRM);
            BusinessUtil.assertFlase(record.getStatus() == 2, HANDOVER_DELIVER_ALREADY_CONFIRM);
            if(checkTimeline() && record.getStatus() != 1){
                record.setStatus(-1);//置为待确认状态
                deliverReceiveRecordMapper.updateByPrimaryKeySelective(record);
                throw new BusinessException(HANDOVER_DELIVER_WAITING_CONFIRM);
            }
            int errorCnt = deliverDetailMapper.countErrorData(id);
            BusinessUtil.assertFlase(errorCnt > 0, HANDOVER_DELIVER_EXISTS_DATA_ERROR);
            //TODO 生成文件 并 请求第三方提交数据接口
//        List<DeliverDetail> deliverData = null;
//        String newFileName = ExcelUtils.writeExcel(pushPath, deliverData, DeliverTemplateBean.class);
        }
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

    private Map<String, String> reqThridServer() {
        Map<String, String> response = new HashMap<>();
        response.put("status", mockThridResult() ? "ok" : "no");
        response.put("fileName", mockThridResult() ? "deliver_ok.xlsx" : "deliver_error.xlsx");
        response.put("msg", "这是一个描述");
        return response;
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

}

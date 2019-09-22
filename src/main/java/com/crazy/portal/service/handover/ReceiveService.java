package com.crazy.portal.service.handover;

import com.alibaba.fastjson.JSONObject;
import com.crazy.portal.bean.handover.HandoverUploadVO;
import com.crazy.portal.bean.handover.ReceiveTemplateBean;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.handover.ReceiveDetailMapper;
import com.crazy.portal.entity.handover.DeliverReceiveRecord;
import com.crazy.portal.entity.handover.ReceiveDetail;
import com.crazy.portal.service.customer.CustomerInfoService;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.crazy.portal.util.Enums.BI_FUNCTION_CODE.*;
import static com.crazy.portal.util.ErrorCodes.BusinessEnum.*;

/**
 * Created by lee on 2019/8/24.
 */

@Slf4j
@Service("receive")
@Transactional
public class ReceiveService extends AbstractHandover implements IHandover<ReceiveDetail> {

    @Resource
    private HandoverService handoverService;
    @Resource
    private ReceiveDetailMapper receiveDetailMapper;
    @Resource
    private CustomerInfoService customerInfoService;

    @Value("${file.path.receive.template}")
    private String receiveTemplatePath;
    @Value("${file.path.receive.push}")
    private String receivePushPath;
    @Value("${file.path.receive.pull}")
    private String receivePullPath;
    @Value("${file.path.receive.local}")
    private String receiveLocalPath;
    @Value("${ftp.path.handover.push}")
    private String ftpPushPath;
    @Value("${ftp.path.handover.pull}")
    private String ftpPullPath;

    public boolean pushReceiveDataToBi(List<ReceiveDetail> receiveData, Integer userId) {
        String thirdFileName = ExcelUtils.writeExcel(receivePushPath, receiveData, ReceiveDetail.class);
        BiCheckResult checkResult = callBiServerByFtp(CHECK_INVENTORY_IMPORT_FILE, receivePushPath, thirdFileName, receivePullPath);
        List<ReceiveDetail> responseData = ExcelUtils.readExcel(checkResult.getFilePath(), ReceiveDetail.class);
        //批次记录表
        DeliverReceiveRecord record = handoverService.genRecord(customerInfoService.getDealerByUser(userId).getCustName(), userId, 2);
        for(ReceiveDetail detail : responseData){
            detail.setRecordId(record.getId());
            receiveDetailMapper.insertSelective(detail);
        }
        if(checkResult.isSuccess()){
            List<ReceiveDetail> importData = receiveDetailMapper.selectByRecordId(record.getId());
            String importFile = ExcelUtils.writeExcel(receivePushPath, importData, ReceiveDetail.class);
            BiCheckResult importResult = callBiServerByFtp(CHECK_INVENTORY_IMPORT_FILE, receivePushPath, importFile, receivePullPath);
            List<ReceiveDetail> importResData = ExcelUtils.readExcel(importResult.getFilePath(), ReceiveDetail.class);
            if(importResult.isSuccess()){
                handoverService.updateStatus(record.getId(), 2);
                return true;
            }
            receiveDetailMapper.deleteByRecordId(record.getId());
            for(ReceiveDetail detail : importResData){
                detail.setRecordId(record.getId());
                receiveDetailMapper.insertSelective(detail);
            }
            return false;
        }
        return false;
    }

    @Override
    public HandoverUploadVO verificationData(List<ReceiveDetail> receiveData, Integer userId) {
        /*String thirdFileName = ExcelUtils.writeExcel(receivePushPath, receiveData, ReceiveDetail.class);
        BiCheckResult checkResult = callBiServerByFtp(CHECK_INVENTORY_IMPORT_FILE, receivePushPath, thirdFileName, receivePullPath);
        List<ReceiveDetail> responseData = ExcelUtils.readExcel(checkResult.getFilePath(), ReceiveDetail.class);
        //批次记录表
        DeliverReceiveRecord record = handoverService.genRecord(customerInfoService.getDealerByUser(userId).getCustName(), userId, 2);
        for(ReceiveDetail detail : responseData){
            detail.setRecordId(record.getId());
            receiveDetailMapper.insertSelective(detail);
        }
        return genThirdResult(checkResult, responseData, record.getId());*/
        return null;
    }

    @Override
    public HandoverUploadVO verificationDataByErrorData(List<?> data, Integer userId, Integer recordId) {
        List<ReceiveTemplateBean> errorList = (List<ReceiveTemplateBean>) data;
        for(ReceiveTemplateBean errorData : errorList){
            ReceiveDetail dbRecord = receiveDetailMapper.selectByPrimaryKey(Integer.parseInt(errorData.getErrorId()));
            //Excel内的ID未找到记录则不尽兴处理
            BusinessUtil.assertFlase(null == dbRecord || !dbRecord.getRecordId().equals(recordId), HANDOVER_DATA_NOT_EXISTS);
            ReceiveDetail detail = JSONObject.parseObject(JSONObject.toJSONString(errorData), ReceiveDetail.class);
            detail.setId(dbRecord.getId());
            detail.setErrorMsg(null);
            receiveDetailMapper.updateByPrimaryKeySelective(detail);
        }

        List<ReceiveDetail> fullData = receiveDetailMapper.selectByRecordId(recordId);
        String thirdFileName = ExcelUtils.writeExcel(receivePushPath, fullData, ReceiveDetail.class);
        BiCheckResult checkResult = callBiServerByFtp(CHECK_INVENTORY_IMPORT_FILE, receivePushPath, thirdFileName, receivePullPath);
        List<ReceiveDetail> responseData = ExcelUtils.readExcel(checkResult.getFilePath(), ReceiveDetail.class);
        receiveDetailMapper.deleteByRecordId(recordId);
        for(ReceiveDetail detail : responseData){
            detail.setRecordId(recordId);
            receiveDetailMapper.insertSelective(detail);
        }
        return genThirdResult(checkResult, responseData, recordId);
    }

    @Override
    public HandoverUploadVO saveData(Integer recordId, Integer userId) {
        int errorCnt = receiveDetailMapper.countErrorData(recordId);
        BusinessUtil.assertFlase(errorCnt > 0, HANDOVER_EXISTS_DATA_ERROR);
        List<ReceiveDetail> receiveData = receiveDetailMapper.selectByRecordId(recordId);
        String thirdFileName = ExcelUtils.writeExcel(receivePushPath, receiveData, ReceiveDetail.class);
        BiCheckResult checkResult = callBiServerByFtp(CHECK_INVENTORY_IMPORT_FILE, receivePushPath, thirdFileName, receivePullPath);
        List<ReceiveDetail> responseData = ExcelUtils.readExcel(checkResult.getFilePath(), ReceiveDetail.class);
        if(checkResult.isSuccess()){
            handoverService.updateStatus(recordId, 2);
            return null;
        }
        receiveDetailMapper.deleteByRecordId(recordId);
        for(ReceiveDetail detail : responseData){
            detail.setRecordId(recordId);
            receiveDetailMapper.insertSelective(detail);
        }
        return genThirdResult(checkResult, responseData, recordId);
    }

    @Override
    public void downloadError(HttpServletResponse response, String fileName) {
        FileUtil.download(response, receivePullPath, fileName);
    }

    @Override
    public PageInfo<ReceiveDetail> getDetailList(Integer dealerId, Integer pageNum, Integer pageSize,
                                                 String uploadStartTime, String uploadEndTime,
                                                 String handoverStartTime, String handoverEndTime,
                                                 String customerFullName, String productModel, String deliveryType,
                                                 String orderMonth, String customerOrderNumber,
                                                 String warehouse, String deliveryCompany) {
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<ReceiveDetail> result = receiveDetailMapper.selectByDealerId(dealerId,
                uploadStartTime, uploadEndTime, handoverStartTime, handoverEndTime,
                productModel, warehouse, deliveryCompany);
        return new PageInfo<>(result);
    }

    @Override
    public void downloadRejectData(Integer recordId, HttpServletResponse response) {
        List<ReceiveDetail> result = receiveDetailMapper.selectByRecordId(recordId);
        String fileName = ExcelUtils.writeExcel(receiveLocalPath, result, ReceiveDetail.class);
        FileUtil.download(response, receiveLocalPath, fileName);
    }

    @Override
    public PageInfo<ReceiveDetail> getDetailInfo(Integer recordId, Integer pageNum, Integer pageSize) {
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<ReceiveDetail> result = receiveDetailMapper.selectByRecordId(recordId);
        return new PageInfo<>(result);
    }

    @Override
    public void downloadTemplate(HttpServletResponse response) {
        FileUtil.download(response, receiveTemplatePath, "receive_template.xlsx");
    }

    @Override
    public List<ReceiveTemplateBean> uploadTemplateData(MultipartFile excel, Integer userId) {
        if(log.isDebugEnabled()){
            log.debug("Current user identity as [{}]", userId);
        }
        //判断当前用户是否为 代理商
        BusinessUtil.assertFlase(false, HANDOVER_NOT_DEALER);
        List<ReceiveTemplateBean> receiveData =  ExcelUtils.readExcel(excel, ReceiveTemplateBean.class , 1);
        for(ReceiveTemplateBean templateBean : receiveData){
            templateBean.setDealerName(customerInfoService.getDealerByUser(userId).getCustName());
        }
        return receiveData;
    }

    @Override
    public void batchDeleteData(Integer[] ids, Integer userId) {
        StringBuilder sb = new StringBuilder();
        for(Integer id : ids){
            ReceiveDetail detail = receiveDetailMapper.selectByPrimaryKey(id);
            if(null == detail){
                throw new BusinessException(HANDOVER_DATA_NOT_EXISTS);
            }else{
                sb.append(detail.getThirdId());
                sb.append(",");
            }
        }
        String custName = customerInfoService.getDealerByUser(userId).getCustName();
        Map<String, String> param = new HashMap<>();
        param.put("ID", sb.replace(sb.length() - 1, sb.length(), "").toString());
        param.put("UserName", custName);
        try {
            String response = CallApiUtils.callBiPostApi(DELETE_SALES_CASE, "PORTAL/BI/", JSONObject.toJSONString(param));
            response = response.replace("\"", "");
            if("删除成功".equals(response)){
                receiveDetailMapper.batchDeleteByIds(ids);
            }
        }catch (Exception ex) {
            throw new BusinessException(HANDOVER_BI_SERVER_EXCEPTION);
        }
    }

    @Override
    public void downloadDataByUpdate(HttpServletResponse response, Integer[] ids) {

    }

    @Override
    public HandoverUploadVO uploadDataByUpdate(MultipartFile excel, Integer userId) {
        return null;
    }

    private HandoverUploadVO genThirdResult(BiCheckResult checkResult, List<?> responseData, Integer recordId) {
        HandoverUploadVO resultInfo = new HandoverUploadVO();
        resultInfo.setRecordId(recordId);
        resultInfo.setIsError(checkResult.isSuccess() ? 0 : 1);
        resultInfo.setReceiveDetails(responseData);
        if(!checkResult.isSuccess()){
            List<ReceiveTemplateBean> errorData = receiveDetailMapper.selectErrorDataByRecord(recordId);
            String errorDataFileName = ExcelUtils.writeExcel(receivePullPath, errorData, ReceiveTemplateBean.class);
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

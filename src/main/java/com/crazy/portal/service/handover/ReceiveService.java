package com.crazy.portal.service.handover;

import com.alibaba.fastjson.JSONObject;
import com.crazy.portal.bean.handover.HandoverUploadVO;
import com.crazy.portal.bean.handover.ReceiveTemplateBean;
import com.crazy.portal.dao.handover.ReceiveDetailMapper;
import com.crazy.portal.entity.handover.DeliverReceiveRecord;
import com.crazy.portal.entity.handover.ReceiveDetail;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.ExcelUtils;
import com.crazy.portal.util.FileUtil;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.crazy.portal.util.Enums.BI_FUNCTION_CODE.CHECK_INVENTORY_IMPORT_FILE;
import static com.crazy.portal.util.ErrorCodes.BusinessEnum.HANDOVER_DATA_NOT_EXISTS;
import static com.crazy.portal.util.ErrorCodes.BusinessEnum.HANDOVER_NOT_DEALER;

/**
 * Created by lee on 2019/8/24.
 */

@Slf4j
@Service("receive")
public class ReceiveService extends AbstractHandover implements IHandover<ReceiveDetail> {

    @Resource
    private HandoverService handoverService;
    @Resource
    private ReceiveDetailMapper receiveDetailMapper;

    @Value("${file.path.receive.template}")
    private String receiveTemplatePath;
    @Value("${file.path.receive.push}")
    private String receivePushPath;
    @Value("${file.path.receive.pull}")
    private String receivePullPath;
    @Value("${file.path.receive.local}")
    private String receiveLocalPath;


    @Override
    public HandoverUploadVO verificationData(List<ReceiveDetail> receiveData, Integer userId) {
        String thirdFileName = ExcelUtils.writeExcel(receivePushPath, receiveData, ReceiveDetail.class);
        BiCheckResult checkResult = callBiServer(CHECK_INVENTORY_IMPORT_FILE, (receivePushPath+thirdFileName), receivePullPath);
        List<ReceiveDetail> responseData = ExcelUtils.readExcel(checkResult.getFilePath(), ReceiveDetail.class);
        //批次记录表
        DeliverReceiveRecord record = handoverService.genRecord("模拟收货的代理商名字", userId, 2);
        for(ReceiveDetail detail : responseData){
            detail.setRecordId(record.getId());
            receiveDetailMapper.insertSelective(detail);
        }
        return genThirdResult(checkResult, responseData, record.getId());
    }

    @Override
    public HandoverUploadVO verificationDataByErrorData(List<?> data, Integer userId, Integer recordId) {
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
        List<ReceiveDetail> responseData = ExcelUtils.readExcel(checkResult.getFilePath(), ReceiveDetail.class);
        receiveDetailMapper.deleteByRecordId(recordId);
        for(ReceiveDetail detail : responseData){
            detail.setRecordId(recordId);
            receiveDetailMapper.insertSelective(detail);
        }
        return genThirdResult(checkResult, responseData, recordId);
    }

    @Override
    public void submitData(Integer id, String type) {

    }

    @Override
    public void downloadError(HttpServletResponse response, String fileName) {
        FileUtil.download(response, receivePullPath, fileName);
    }

    @Override
    public PageInfo<ReceiveDetail> getDetailList(Integer dealerId, Integer pageNum, Integer pageSize) {
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<ReceiveDetail> result = receiveDetailMapper.selectByDealerId(dealerId);
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
            templateBean.setDealerName("模拟收货的代理商名字");
        }
        return receiveData;
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
}

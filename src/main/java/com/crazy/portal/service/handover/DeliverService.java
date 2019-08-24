package com.crazy.portal.service.handover;

import com.alibaba.fastjson.JSONObject;
import com.crazy.portal.bean.handover.DeliverTemplateBean;
import com.crazy.portal.bean.handover.HandoverUploadVO;
import com.crazy.portal.bean.handover.ReceiveTemplateBean;
import com.crazy.portal.dao.handover.DeliverDetailMapper;
import com.crazy.portal.entity.handover.DeliverDetail;
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

import static com.crazy.portal.util.Enums.BI_FUNCTION_CODE.CHECK_SALES_IMPORT_FILE;
import static com.crazy.portal.util.ErrorCodes.BusinessEnum.HANDOVER_DATA_NOT_EXISTS;
import static com.crazy.portal.util.ErrorCodes.BusinessEnum.HANDOVER_NOT_DEALER;

/**
 * Created by lee on 2019/8/24.
 */

@Slf4j
@Service("deliver")
public class DeliverService extends AbstractHandover implements IHandover<DeliverDetail> {

    @Resource
    private HandoverService handoverService;
    @Resource
    private DeliverDetailMapper deliverDetailMapper;

    @Value("${file.path.deliver.template}")
    private String deliverTemplatePath;
    @Value("${file.path.deliver.push}")
    private String deliverPushPath;
    @Value("${file.path.deliver.pull}")
    private String deliverPullPath;
    @Value("${file.path.deliver.local}")
    private String deliverLocalPath;

    @Override
    public HandoverUploadVO verificationData(List<DeliverDetail> deliverData, Integer userId) {
        //数据包装，生成第三方需要的文件
        String thirdFileName = ExcelUtils.writeExcel(deliverPushPath, deliverData, DeliverDetail.class);
        //请求了第三方，并拿到了结果
        BiCheckResult checkResult = callBiServer(CHECK_SALES_IMPORT_FILE, (deliverPushPath+thirdFileName), deliverPullPath);
        List<DeliverTemplateBean> responseData = ExcelUtils.readExcel(checkResult.getFilePath(), DeliverTemplateBean.class);
        //批次记录表
        DeliverReceiveRecord record = handoverService.genRecord("模拟出货的代理商名字", userId, 1);
        for(DeliverTemplateBean templateBean : responseData){
            DeliverDetail detail = JSONObject.parseObject(JSONObject.toJSONString(templateBean), DeliverDetail.class);
            detail.setErrorMsg(templateBean.getThirdErrorMsg());
            detail.setRecordId(record.getId());
            deliverDetailMapper.insertSelective(detail);
        }
        return genThirdResult(checkResult, responseData, record.getId());
    }

    @Override
    public HandoverUploadVO verificationDataByErrorData(List<?> data, Integer userId, Integer recordId) {
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
        return genThirdResult(checkResult, responseData, recordId);
    }

    @Override
    public void submitData(Integer id, String type) {

    }

    @Override
    public void downloadError(HttpServletResponse response, String fileName) {
        FileUtil.download(response, deliverPullPath, fileName);
    }

    @Override
    public PageInfo<DeliverDetail> getDetailList(Integer dealerId, Integer pageNum, Integer pageSize) {
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<DeliverDetail> result = deliverDetailMapper.selectByDealerId(dealerId);
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
        List<DeliverTemplateBean> deliverData =  ExcelUtils.readExcel(excel, DeliverTemplateBean.class , 1);
        for(DeliverTemplateBean templateBean : deliverData){
            templateBean.setDealerName("模拟出货的代理商名字");
        }
        return deliverData;
    }

    private HandoverUploadVO genThirdResult(BiCheckResult checkResult, List<DeliverTemplateBean> responseData, Integer recordId) {
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
}

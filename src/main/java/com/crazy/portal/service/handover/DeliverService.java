package com.crazy.portal.service.handover;

import com.crazy.portal.bean.handover.DeliverTemplateBean;
import com.crazy.portal.bean.handover.DeliverUploadVO;
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
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.crazy.portal.util.ErrorCodes.BusinessEnum.*;

/**
 * Created by lee on 2019/8/3.
 */

@Slf4j
@Service
public class DeliverService {

    @Resource
    private DeliverReceiveRecordMapper deliverReceiveRecordMapper;
    @Resource
    private DeliverDetailMapper deliverDetailMapper;

    @Value("${file.path.deliver.template}")
    private String templatePath;
    @Value("${file.path.deliver.push}")
    private String pushPath;
    @Value("${file.path.deliver.pull}")
    private String pullPath;

    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 下载出货模版
     * @param response
     */
    public void downloadTemplate(HttpServletResponse response) {
        FileUtil.download(response, templatePath, "deliver_template.xlsx");
    }

    /**
     * 上传出货数据，并与第三方交互获取数据校验结果
     * @param excel
     * @param userId
     * @return
     */
    public List<DeliverTemplateBean> uploadTemplateData(MultipartFile excel, Integer userId) {
        if(log.isDebugEnabled()){
            log.debug("Current user identity as [{}]", userId);
        }
        //此处代码等业务确认之后进行替换（如何识别代理商身份）
        String dealerName = mockDealerInfo(userId).get("dealerName");
        //判断当前用户是否为 代理商
        BusinessUtil.assertFlase(false, HANDOVER_DELIVER_NOT_DEALER);
        List<DeliverTemplateBean> deliverData =  ExcelUtils.readExcel(excel, DeliverTemplateBean.class , 1);
        for(DeliverTemplateBean templateBean : deliverData){
            templateBean.setDealerName(dealerName);
        }
        return deliverData;
    }

    public DeliverUploadVO verificationData(List<DeliverTemplateBean> deliverData, Integer userId) {
        String dealerName = deliverData.get(0).getDealerName();
        DeliverUploadVO resultInfo = new DeliverUploadVO();
        //TODO 生成第三方的数据文件 并 请求第三方
        String fileName = "deliver_error_data_1.xlsx";
        //拿到对方返回文件
        List<DeliverDetail> responseData = ExcelUtils.readExcel(pullPath, fileName, DeliverDetail.class);
        String verificationResult = "no";//是否全部校验通过
        if(verificationResult.equals("ok")){
            DeliverReceiveRecord record = new DeliverReceiveRecord();
            record.setUploadTime(new Date());
            record.setDealerName(dealerName);
            record.setDealerId(userId);
            record.setStatus(0);
            deliverReceiveRecordMapper.insertSelective(record);
            //数据都正确，则进行保存
            for(DeliverDetail detail : responseData){
                detail.setRecordId(record.getId());
                deliverDetailMapper.insert(detail);
            }
            resultInfo.setDetails(responseData);
            resultInfo.setRecordId(record.getId());
            return resultInfo;
        }
        resultInfo.setIsError(1);
        resultInfo.setErrorFileName(fileName);
        resultInfo.setMsg("请点击【下载错误数据】，并将更新后的数据重新上传");
        return resultInfo;
    }

    /**
     * 下载出货错误数据（第三方提供文件）
     * @param response
     * @param fileName
     */
    public void downloadError(HttpServletResponse response, String fileName) {
        FileUtil.download(response, pullPath, fileName);
    }

    /**
     * 将校验通过的出货数据提交至第三方
     * @param id
     */
    public void submitData(Integer id) {
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
        List<DeliverDetail> deliverData = null;
    }

    /**
     * 进出货记录分页列表
     * @param dealerName
     * @param status
     * @param deliveryStartDate
     * @param deliveryEndDate
     * @param uploadStartTime
     * @param uploadEndTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<DeliverReceiveRecord> getPageList(String dealerName, Integer status,
                                               String deliveryStartDate, String deliveryEndDate,
                                               String uploadStartTime, String uploadEndTime,
                                               Integer pageNum, Integer pageSize) {
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<DeliverReceiveRecord> result = deliverReceiveRecordMapper.selectPageInfo(dealerName, status, deliveryStartDate, deliveryEndDate,
                uploadStartTime, uploadEndTime);
        return new PageInfo<>(result);
    }

    /**
     * 进出货明细记录
     * @param id
     * @return
     */
    public List<?> getDetailInfo(Integer id) {
        //TODO 查询数据库记录并返回
        return null;
    }

    public void approvalDeliverInfo(Integer id, Integer userId) {
        DeliverReceiveRecord record = deliverReceiveRecordMapper.selectByPrimaryKey(id);
        if(null == record){
            return;
        }
        if(record.getStatus() != 0){
            //TODO 数据不符合 待确认 状态
        }
        if(false){
            //TODO 用户不是 销售运作部 不允许操作
        }
        //TODO 更新记录状态 待确认 -> 已确认
    }

    private Map<String, String> mockDealerInfo(Integer userId) {
        Map<String, String> map = new HashMap<>();
        map.put("dealerName", "模拟的代理商");
        return map;
    }

    private boolean checkTimeline() {
        //模拟一个时间用来判断
        try {
            Date mockDate = df.parse("2019-01-01");
            return new Date().compareTo(mockDate) > 1;
        } catch (ParseException e) {
            return false;
        }
    }

}

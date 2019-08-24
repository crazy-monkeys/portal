package com.crazy.portal.service.handover;

import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.handover.DeliverDetailMapper;
import com.crazy.portal.dao.handover.DeliverReceiveRecordMapper;
import com.crazy.portal.dao.handover.ReceiveDetailMapper;
import com.crazy.portal.entity.handover.DeliverReceiveRecord;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.crazy.portal.util.ErrorCodes.BusinessEnum.*;
/**
 * Created by lee on 2019/8/10.
 */

@Slf4j
@Service
public class HandoverService extends AbstractHandover {

    @Resource
    private DeliverReceiveRecordMapper deliverReceiveRecordMapper;
    @Resource
    private DeliverDetailMapper deliverDetailMapper;
    @Resource
    private ReceiveDetailMapper receiveDetailMapper;

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


    public DeliverReceiveRecord genRecord(String dealerName, Integer userId, Integer type) {
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

    private boolean checkTimeline() {
        //TODO 提交时间校验逻辑
        return true;
    }

}

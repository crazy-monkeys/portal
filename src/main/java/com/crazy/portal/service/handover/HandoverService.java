package com.crazy.portal.service.handover;

import com.crazy.portal.dao.handover.DeliverDetailUpdateMapper;
import com.crazy.portal.dao.handover.DeliverReceiveRecordMapper;
import com.crazy.portal.entity.handover.DeliverReceiveRecord;
import com.crazy.portal.service.system.UserCustomerMappingService;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.crazy.portal.util.ErrorCodes.BusinessEnum.*;
/**
 * Created by lee on 2019/8/10.
 */

@Slf4j
@Service
public class HandoverService {

    @Resource
    private DeliverReceiveRecordMapper deliverReceiveRecordMapper;
    @Resource(name = "deliver")
    private DeliverService deliverService;
    @Resource(name = "receive")
    private ReceiveService receiveService;
    @Resource
    private UserCustomerMappingService userCustomerMappingService;
    @Resource
    private DeliverDetailUpdateMapper deliverDetailUpdateMapper;

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
                                                      Integer pageNum, Integer pageSize, Integer userId) {
        List<Integer> userList = userCustomerMappingService.selectUserMapping(userId, Enums.CustomerMappingModel.Forecast.getValue());
        Integer[] userIds = new Integer[userList.size()];
        PortalUtil.defaultStartPage(pageNum,pageSize);

        List<DeliverReceiveRecord> result = deliverReceiveRecordMapper.selectPageInfo(dealerName, status,
                uploadStartTime, uploadEndTime, null, null, Arrays.asList(userIds));
        return new PageInfo<>(result);
    }

    public PageInfo<DeliverReceiveRecord> getRejectInfo(Integer dealerId, String type,
                                                        Integer pageNum, Integer pageSize, Integer userId) {
        checkTypeValue(type);
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<DeliverReceiveRecord> result = deliverReceiveRecordMapper.selectPageInfo(null, 3,
                null, null, dealerId, deliver_type.equals(type) ? 1 : 2, Arrays.asList(userId));
        return new PageInfo<>(result);
    }

    public PageInfo<DeliverReceiveRecord> getReceiveErrorList(Integer dealerId, Integer pageNum,
                                                              Integer pageSize, Integer userId) {
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<DeliverReceiveRecord> result = deliverReceiveRecordMapper.selectPageInfo(null, -2,
                null, null, dealerId, 2, Arrays.asList(userId));
        return new PageInfo<>(result);
    }


//    @Transactional
    public void operationDeliverInfo(Integer id, Integer userId, String type, Integer status, String remark) {
        checkTypeValue(type);
        DeliverReceiveRecord record = deliverReceiveRecordMapper.selectByPrimaryKey(id);
        BusinessUtil.assertFlase(null == record, HANDOVER_INVALID_PARAM);
        BusinessUtil.assertFlase(status == record.getStatus(), HANDOVER_REJECT_REPEAT_ERROR);
        //执行驳回
        if(status == 3){
            BusinessUtil.assertFlase(record.getStatus() == 2, HANDOVER_NOT_REJECT);
            BusinessUtil.assertEmpty(remark, HANDOVER_REJECT_REMARK_NOT_EMPTY);
        }
        //执行确认
        if(status == 1){
            if(record.getStatus() == 4 && deliver_type.equals(type)){
                deliverService.updateDataToBi(id);
                status = 2;
            }else if(record.getStatus() == 4 && receive_type.equals(type)) {
                receiveService.updateDataToBi(id);
                status = 2;
            }else{
                BusinessUtil.assertFlase(record.getStatus() != -1, HANDOVER_NOT_CONFIRM);
            }
        }
/*        if(false){
            //TODO 用户不是 销售运作部 不允许操作
        }*/
        record.setStatus(status);
        record.setRemark(remark);
        record.setApprovalUserId(userId);
        record.setApprovalTime(new Date());
        deliverReceiveRecordMapper.updateByPrimaryKeySelective(record);
        if(status == 3){
            deliverDetailUpdateMapper.deleteByRecordId(id);
        }
    }

    @Transactional
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

    @Transactional
    public void updateStatus(Integer recordId, Integer status){
        deliverReceiveRecordMapper.updateStatusById(Arrays.asList(recordId), status);
    }

    @Transactional
    public void updateStatus(List<Integer> ids, Integer status){
        deliverReceiveRecordMapper.updateStatusById(ids, status);
    }

    /**
     * 将校验通过的出货数据提交至第三方
     * @param recordId
     */
    public void checkDataStatus(Integer recordId) {
        DeliverReceiveRecord record = deliverReceiveRecordMapper.selectByPrimaryKey(recordId);
        BusinessUtil.assertFlase(null == record, HANDOVER_INVALID_PARAM);
        BusinessUtil.assertFlase(record.getStatus() == -1, HANDOVER_WAITING_CONFIRM);
        BusinessUtil.assertFlase(record.getStatus() == 2, HANDOVER_ALREADY_CONFIRM);
        /*if(checkTimeline() && record.getStatus() != 1){
            record.setStatus(-1);//置为待确认状态
            deliverReceiveRecordMapper.updateByPrimaryKeySelective(record);
            throw new BusinessException(HANDOVER_WAITING_CONFIRM);
        }*/
    }

    public List<Integer> getStatusByIds(Set<Integer> ids) {
        return deliverReceiveRecordMapper.selectStatusByIds(ids);
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

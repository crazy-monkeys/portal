package com.crazy.portal.dao.handover;

import com.crazy.portal.entity.handover.DeliverRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeliverRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliverRecord record);

    int insertSelective(DeliverRecord record);

    DeliverRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeliverRecord record);

    int updateByPrimaryKey(DeliverRecord record);

    List<DeliverRecord> selectPageInfo(@Param(value = "dealerName") String dealerName,
                                       @Param(value = "status") Integer status,
                                       @Param(value = "deliveryStartDate") String deliveryStartDate,
                                       @Param(value = "deliveryEndDate") String deliveryEndDate,
                                       @Param(value = "uploadStartTime") String uploadStartTime,
                                       @Param(value = "uploadEndTime") String uploadEndTime);
}
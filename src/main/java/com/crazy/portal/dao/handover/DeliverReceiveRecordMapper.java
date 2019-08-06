package com.crazy.portal.dao.handover;

import com.crazy.portal.entity.handover.DeliverReceiveRecord;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface DeliverReceiveRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliverReceiveRecord record);

    int insertSelective(DeliverReceiveRecord record);

    DeliverReceiveRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeliverReceiveRecord record);

    int updateByPrimaryKey(DeliverReceiveRecord record);

    List<DeliverReceiveRecord> selectPageInfo(@Param(value = "dealerName") String dealerName,
                                              @Param(value = "status") Integer status,
                                              @Param(value = "deliveryStartDate") String deliveryStartDate,
                                              @Param(value = "deliveryEndDate") String deliveryEndDate,
                                              @Param(value = "uploadStartTime") String uploadStartTime,
                                              @Param(value = "uploadEndTime") String uploadEndTime);

}
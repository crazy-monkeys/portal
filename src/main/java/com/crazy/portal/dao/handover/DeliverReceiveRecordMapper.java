package com.crazy.portal.dao.handover;

import com.crazy.portal.entity.handover.DeliverReceiveRecord;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Set;

public interface DeliverReceiveRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliverReceiveRecord record);

    int insertSelective(DeliverReceiveRecord record);

    DeliverReceiveRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeliverReceiveRecord record);

    int updateByPrimaryKey(DeliverReceiveRecord record);

    List<DeliverReceiveRecord> selectPageInfo(@Param(value = "dealerName") String dealerName,
                                              @Param(value = "status") Integer status,
                                              @Param(value = "uploadStartTime") String uploadStartTime,
                                              @Param(value = "uploadEndTime") String uploadEndTime,
                                              @Param(value = "dealerId") Integer dealerId,
                                              @Param(value = "type") Integer type,
                                              @Param(value = "userIds") List<Integer> userIds);

    int updateStatusById(@Param(value = "ids") List<Integer> ids,
                         @Param(value = "status") Integer status);

    List<Integer> selectStatusByIds(@Param(value = "ids") Set<Integer> ids);

}
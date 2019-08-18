package com.crazy.portal.dao.handover;

import com.crazy.portal.bean.handover.DeliverTemplateBean;
import com.crazy.portal.entity.handover.DeliverDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeliverDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliverDetail record);

    int insertSelective(DeliverDetail record);

    DeliverDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeliverDetail record);

    int updateByPrimaryKey(DeliverDetail record);

    List<DeliverDetail> selectByRecordId(@Param(value = "recordId") Integer recordId);

    List<DeliverDetail> selectByDealerId(@Param(value = "dealerId") Integer dealerId);

    int countErrorData(@Param(value = "recordId") Integer recordId);

    List<DeliverTemplateBean> selectErrorDataByRecord(@Param(value = "recordId") Integer recordId);

    int deleteByRecordId(@Param(value = "recordId") Integer recordId);
}
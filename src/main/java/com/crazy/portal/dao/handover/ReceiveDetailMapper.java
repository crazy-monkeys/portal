package com.crazy.portal.dao.handover;

import com.crazy.portal.bean.handover.ReceiveTemplateBean;
import com.crazy.portal.entity.handover.ReceiveDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReceiveDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReceiveDetail record);

    int insertSelective(ReceiveDetail record);

    ReceiveDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReceiveDetail record);

    int updateByPrimaryKey(ReceiveDetail record);

    List<ReceiveDetail> selectByRecordId(@Param(value = "recordId") Integer recordId);

    List<ReceiveDetail> selectByDealerId(@Param(value = "dealerId") Integer dealerId,
                                         @Param(value = "uploadStartTime") String uploadStartTime,
                                         @Param(value = "uploadEndTime") String uploadEndTime,
                                         @Param(value = "handoverStartTime") String handoverStartTime,
                                         @Param(value = "handoverEndTime") String handoverEndTime,
                                         @Param(value = "productModel") String productModel,
                                         @Param(value = "warehouse") String warehouse,
                                         @Param(value = "deliveryCompany") String deliveryCompany);

    List<ReceiveTemplateBean> selectErrorDataByRecord(@Param(value = "recordId") Integer recordId);

    int countErrorData(@Param(value = "recordId") Integer recordId);

    int deleteByRecordId(@Param(value = "recordId") Integer recordId);

    int batchDeleteByIds(@Param(value = "ids") Integer[] ids);

}
package com.crazy.portal.dao.business.idr;

import com.crazy.portal.entity.business.idr.BusinessIdrApproval;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessIdrApprovalMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BusinessIdrApproval record);

    BusinessIdrApproval selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessIdrApproval record);

    BusinessIdrApproval selectByOrderNo(@Param("orderNo")String orderNo);

    List<BusinessIdrApproval> selectByIdrInfoId(@Param("idrInfoId")Integer idrInfoId);
}
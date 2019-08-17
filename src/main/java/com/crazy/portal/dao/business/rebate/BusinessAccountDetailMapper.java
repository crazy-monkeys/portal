package com.crazy.portal.dao.business.rebate;

import com.crazy.portal.entity.business.rebate.BusinessAccountDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessAccountDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BusinessAccountDetail record);

    BusinessAccountDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessAccountDetail record);

    List<BusinessAccountDetail> selectByRebateId(@Param("rebateId")Integer rebateId);

}
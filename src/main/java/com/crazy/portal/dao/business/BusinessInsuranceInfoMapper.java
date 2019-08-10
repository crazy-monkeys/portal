package com.crazy.portal.dao.business;

import com.crazy.portal.entity.business.BusinessInsuranceInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessInsuranceInfoMapper extends IdrBaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BusinessInsuranceInfo record);

    BusinessInsuranceInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessInsuranceInfo record);

    List<BusinessInsuranceInfo> selectByIdrInfoId(@Param("idrInfoId") Integer idrInfoId);

    int deleteByIdrInfoId(@Param("idrInfoId") Integer idrInfoId);
}
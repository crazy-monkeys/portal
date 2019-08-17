package com.crazy.portal.dao.business.idr;

import com.crazy.portal.entity.business.idr.BusinessReturnsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessReturnsInfoMapper extends IdrBaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BusinessReturnsInfo record);

    BusinessReturnsInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessReturnsInfo record);

    List<BusinessReturnsInfo> selectByIdrInfoId(@Param("idrInfoId") Integer idrInfoId);

    int deleteByIdrInfoId(@Param("idrInfoId") Integer idrInfoId);
}
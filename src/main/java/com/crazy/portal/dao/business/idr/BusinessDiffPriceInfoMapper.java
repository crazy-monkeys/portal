package com.crazy.portal.dao.business.idr;

import com.crazy.portal.entity.business.idr.BusinessDiffPriceInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessDiffPriceInfoMapper extends IdrBaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BusinessDiffPriceInfo record);

    BusinessDiffPriceInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessDiffPriceInfo record);

    List<BusinessDiffPriceInfo> selectByIdrInfoId(@Param("idrInfoId") Integer idrInfoId);

    int deleteByIdrInfoId(@Param("idrInfoId") Integer idrInfoId);
}
package com.crazy.portal.dao.business.rebate;

import com.crazy.portal.entity.business.rebate.BusinessSalesDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessSalesDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessSalesDetail record);

    int insertSelective(BusinessSalesDetail record);

    BusinessSalesDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessSalesDetail record);

    int updateByPrimaryKey(BusinessSalesDetail record);

    List<BusinessSalesDetail> selectByRebateId(@Param("rebateId")Integer rebateId);
}
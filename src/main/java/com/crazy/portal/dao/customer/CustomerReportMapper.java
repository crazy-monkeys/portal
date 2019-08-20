package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.cusotmer.CustomerReport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerReport record);

    int insertSelective(CustomerReport record);

    CustomerReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerReport record);

    int updateByPrimaryKey(CustomerReport record);

    List<CustomerReport> selectReject(@Param("custId") Integer custId, @Param("reportId") Integer reportId);

    CustomerReport selectUserReport(@Param("custId") Integer custId, @Param("reportDealer") Integer reportDealer, @Param("reportSales") Integer reportSales);
}
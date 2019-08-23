package com.crazy.portal.dao.customer;

import com.crazy.portal.bean.customer.CustomerQueryBean;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerInfo record);

    int insertSelective(CustomerInfo record);

    CustomerInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerInfo record);

    int updateByPrimaryKey(CustomerInfo record);

    CustomerInfo selectDealerInfo(Integer id);

    CustomerInfo selectByCustName(@Param("custName") String custName);

    CustomerInfo queryReportInfo(@Param("custId") int custId, @Param("reportId") int reportId);

    List<CustomerInfo> selectNameAndCodeByUserId(@Param("userId") Integer userId);

    List<CustomerInfo> selectAllCustomer();

    List<CustomerInfo> selectCustomerInfo(CustomerQueryBean record);
}

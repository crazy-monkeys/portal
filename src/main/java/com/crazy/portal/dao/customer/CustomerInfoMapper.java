package com.crazy.portal.dao.customer;

import com.crazy.portal.bean.customer.CustomerQueryBean;
import com.crazy.portal.entity.customer.CustomerInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerInfoMapper {
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("userId") Integer userId);

    int insertSelective(CustomerInfo record);

    CustomerInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerInfo record);

    List<CustomerInfo> selectCustByPage(CustomerQueryBean bean);

    CustomerInfo selectDealerInfo(@Param("dealerId") Integer dealerId);

    CustomerInfo selectByCustName(@Param("custName")String custName);
}
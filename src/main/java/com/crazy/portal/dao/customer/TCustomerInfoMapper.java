package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.customer.TCustomerInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TCustomerInfoMapper {
    int deleteByPrimaryKey(Integer customerId);

    int insert(TCustomerInfo record);

    int insertSelective(TCustomerInfo record);

    TCustomerInfo selectByPrimaryKey(Integer customerId);

    int updateByPrimaryKeySelective(TCustomerInfo record);

    int updateByPrimaryKey(TCustomerInfo record);

    TCustomerInfo selectByName(String customerName);

    List<TCustomerInfo> selectAllCustomer();

    List<TCustomerInfo> approvalList();

    TCustomerInfo selectByRegistId(@Param("registId") Integer registId);
}
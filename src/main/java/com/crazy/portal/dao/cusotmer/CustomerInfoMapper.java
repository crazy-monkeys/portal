package com.crazy.portal.dao.cusotmer;

import com.crazy.portal.bean.customer.CustomerQueryBean;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface CustomerInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerInfo record);

    int insertSelective(CustomerInfo record);

    CustomerInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerInfo record);

    int updateByPrimaryKey(CustomerInfo record);

    CustomerInfo selectDealerInfo(Integer id);

    List<CustomerInfo> selectDealerList();

    List<CustomerInfo> selectByCustName(@Param("custName") String custName);

    CustomerInfo queryCustomerInfo(@Param("custId") int custId);

    List<CustomerInfo> selectNameAndCodeByUserId(@Param("userId") Integer userId);

    List<CustomerInfo> selectAllCustomer();

    CustomerInfo selectEmailByCustName(@Param("custName")String custName);

    List<CustomerInfo> selectCustomer(CustomerQueryBean record);

    LinkedList<CustomerInfo> selectDealerShip(Integer custId);

    CustomerInfo getDealerByUser(Integer userId);

    int updateCustomerInfo(Integer custId);

    int updateC4CId(@Param("custId") Integer custId, @Param("inCode") String inCode);

    CustomerInfo selectByOutCode(String outCode);

    CustomerInfo selectByCustAbbreviation(String custAbbreviation);

    List<CustomerInfo> selectCustShip(Integer custId);
}
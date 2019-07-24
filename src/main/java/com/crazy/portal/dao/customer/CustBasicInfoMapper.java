package com.crazy.portal.dao.customer;

import com.crazy.portal.bean.customer.CustomerQueryBean;
import com.crazy.portal.entity.customer.CustBasicInfo;

import java.util.List;

public interface CustBasicInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustBasicInfo record);

    int insertSelective(CustBasicInfo record);

    CustBasicInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustBasicInfo record);

    int updateByPrimaryKey(CustBasicInfo record);

    List<CustBasicInfo> selectCustByPage(CustomerQueryBean bean);
}
package com.crazy.portal.dao.cust;

import com.crazy.portal.entity.cust.TCustomerInfoDO;
import com.crazy.portal.entity.cust.TCustomerInfoDOWithBLOBs;

public interface TCustomerInfoDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCustomerInfoDOWithBLOBs record);

    int insertSelective(TCustomerInfoDOWithBLOBs record);

    TCustomerInfoDOWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCustomerInfoDOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TCustomerInfoDOWithBLOBs record);

    int updateByPrimaryKey(TCustomerInfoDO record);
}
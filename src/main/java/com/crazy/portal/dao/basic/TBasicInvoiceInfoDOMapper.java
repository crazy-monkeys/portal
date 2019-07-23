package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.TBasicInvoiceInfoDO;

public interface TBasicInvoiceInfoDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBasicInvoiceInfoDO record);

    int insertSelective(TBasicInvoiceInfoDO record);

    TBasicInvoiceInfoDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBasicInvoiceInfoDO record);

    int updateByPrimaryKey(TBasicInvoiceInfoDO record);
}
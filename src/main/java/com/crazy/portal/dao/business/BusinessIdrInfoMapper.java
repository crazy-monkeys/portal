package com.crazy.portal.dao.business;

import com.crazy.portal.bean.business.BusinessIdrQueryBean;
import com.crazy.portal.entity.business.BusinessIdrInfo;

import java.util.List;

public interface BusinessIdrInfoMapper extends IdrBaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BusinessIdrInfo record);

    BusinessIdrInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessIdrInfo record);

    List<BusinessIdrInfo> selectByPage(BusinessIdrQueryBean bean);
}
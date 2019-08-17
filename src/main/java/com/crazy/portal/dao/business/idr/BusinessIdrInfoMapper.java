package com.crazy.portal.dao.business.idr;

import com.crazy.portal.bean.business.idr.BusinessIdrQueryBean;
import com.crazy.portal.entity.business.idr.BusinessIdrInfo;

import java.util.List;

public interface BusinessIdrInfoMapper extends IdrBaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BusinessIdrInfo record);

    BusinessIdrInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessIdrInfo record);

    List<BusinessIdrInfo> selectByPage(BusinessIdrQueryBean bean);
}
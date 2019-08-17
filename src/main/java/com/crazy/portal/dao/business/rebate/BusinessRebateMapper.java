package com.crazy.portal.dao.business.rebate;

import com.crazy.portal.bean.business.rebate.RebateQueryBean;
import com.crazy.portal.entity.business.rebate.BusinessRebate;

import java.util.List;

public interface BusinessRebateMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BusinessRebate record);

    BusinessRebate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessRebate record);

    List<BusinessRebate> selectByPage(RebateQueryBean bean);

}
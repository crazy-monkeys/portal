package com.crazy.portal.dao.business.rebate;

import com.crazy.portal.bean.business.rebate.RebateQueryBean;
import com.crazy.portal.entity.business.rebate.BusinessRebateItem;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BusinessRebateItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BusinessRebateItem record);

    BusinessRebateItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessRebateItem record);

    List<BusinessRebateItem> selectByPage(RebateQueryBean bean);

    List<BusinessRebateItem> selectByRebateId(@Param("rebateId")Integer rebateId);

}
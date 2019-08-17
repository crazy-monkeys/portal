package com.crazy.portal.dao.business.rebate;

import com.crazy.portal.entity.business.rebate.BusinessStrategy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessStrategyMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BusinessStrategy record);

    BusinessStrategy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessStrategy record);

    List<BusinessStrategy> selectByRebateId(@Param("rebateId")Integer rebateId);
}
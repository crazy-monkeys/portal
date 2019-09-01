package com.crazy.portal.dao.business.rebate;

import com.crazy.portal.entity.business.rebate.BusinessPriceRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessPriceRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BusinessPriceRole record);

    BusinessPriceRole selectByPrimaryKey(Integer id);

    int selectCountByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessPriceRole record);

    List<BusinessPriceRole> selectByRebateId(@Param("rebateId")Integer rebateId);
}
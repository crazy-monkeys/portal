package com.crazy.portal.dao.business.rebate;

import com.crazy.portal.entity.business.rebate.BusinessRebateFile;
import org.apache.ibatis.annotations.Param;

public interface BusinessRebateFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BusinessRebateFile record);

    BusinessRebateFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessRebateFile record);

    BusinessRebateFile selectByRebateItemId(@Param("rebateItemId")Integer rebateItemId);
}
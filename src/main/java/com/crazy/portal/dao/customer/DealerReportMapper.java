package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.customer.DealerReport;
import org.apache.ibatis.annotations.Param;

public interface DealerReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(DealerReport record);

    DealerReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DealerReport record);

    DealerReport selectByCustId(@Param("custId")Integer custId);
}
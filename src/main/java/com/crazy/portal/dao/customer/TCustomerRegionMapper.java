package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.customer.TCustomerRegion;
import org.apache.ibatis.annotations.Param;

public interface TCustomerRegionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCustomerRegion record);

    int insertSelective(TCustomerRegion record);

    TCustomerRegion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCustomerRegion record);

    int updateByPrimaryKey(TCustomerRegion record);

    TCustomerRegion selectByRegion(@Param("customerId") int customerId, @Param("userId") int userId, @Param("region") String region);
}
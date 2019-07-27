package com.crazy.portal.dao.customer;

import com.crazy.portal.bean.customer.CustomerQueryBean;
import com.crazy.portal.entity.customer.TCustomerInfoDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TCustomerInfoDOMapper {
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("userId") Integer userId);

    int insertSelective(TCustomerInfoDO record);

    TCustomerInfoDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCustomerInfoDO record);

    List<TCustomerInfoDO> selectCustByPage(CustomerQueryBean bean);

    TCustomerInfoDO selectDealerInfo(@Param("dealerId") Integer dealerId);
}
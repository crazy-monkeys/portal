package com.crazy.portal.dao.business.rebate;

import com.crazy.portal.bean.business.rebate.RebateGroupParam;
import com.crazy.portal.entity.business.rebate.BusinessSalesDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BusinessSalesDetailMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(BusinessSalesDetail record);

    BusinessSalesDetail selectByPrimaryKey(String id);

    int selectCountByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BusinessSalesDetail record);

    List<BusinessSalesDetail> selectByRebateId(@Param("rebateId")Integer rebateId);

    List<RebateGroupParam> selectGroupParamList(@Param("beginMonth")String beginMonth,
                                                @Param("endMonth")String endMonth);

    int updateRebateId();

    int updateRebateAmountByRebateId(@Param("rebateId")Integer rebateId);
}
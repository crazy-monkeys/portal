package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.BasicInvoiceInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BasicInvoiceInfoMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BasicInvoiceInfo record);

    BasicInvoiceInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasicInvoiceInfo record);

    List<BasicInvoiceInfo> selectByCustId(@Param("custId") Integer custId);

    List<Integer> selectIdsByCustId(@Param("custId") Integer custId);

    int deleteByCustId(@Param("custId") Integer custId, @Param("userId")Integer userId);
}
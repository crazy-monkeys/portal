package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.TBasicInvoiceInfoDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TBasicInvoiceInfoDOMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TBasicInvoiceInfoDO record);

    TBasicInvoiceInfoDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBasicInvoiceInfoDO record);

    List<TBasicInvoiceInfoDO> selectByCustId(@Param("custId") Integer custId);

    List<Integer> selectIdsByCustId(@Param("custId") Integer custId);

    int deleteByCustId(@Param("custId") Integer custId, @Param("userId")Integer userId);
}
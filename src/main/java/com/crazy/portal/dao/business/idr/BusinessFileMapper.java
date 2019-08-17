package com.crazy.portal.dao.business.idr;

import com.crazy.portal.entity.business.idr.BusinessFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessFileMapper extends IdrBaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BusinessFile record);

    BusinessFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessFile record);

    List<BusinessFile> selectByIdrInfoId(@Param("idrInfoId") Integer idrInfoId);

    int deleteByIdrInfoId(@Param("idrInfoId") Integer idrInfoId, @Param("fileType")Integer fileType);
}
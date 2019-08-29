package com.crazy.portal.dao.cusotmer;

import com.crazy.portal.entity.cusotmer.CustomerFile;

import java.util.List;

public interface CustomerFileMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(CustomerFile record);

    int insertSelective(CustomerFile record);

    CustomerFile selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(CustomerFile record);

    int updateByPrimaryKey(CustomerFile record);

    List<CustomerFile> selectByCustId(Integer custId);

    int deleteByCustId(Integer custId);
}
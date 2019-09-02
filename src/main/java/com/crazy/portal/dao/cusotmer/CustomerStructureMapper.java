package com.crazy.portal.dao.cusotmer;

import com.crazy.portal.entity.cusotmer.CustomerStructure;

import java.util.List;

public interface CustomerStructureMapper {
    int deleteByPrimaryKey(Integer structureId);

    int insert(CustomerStructure record);

    int insertSelective(CustomerStructure record);

    CustomerStructure selectByPrimaryKey(Integer structureId);

    int updateByPrimaryKeySelective(CustomerStructure record);

    int updateByPrimaryKey(CustomerStructure record);

    List<CustomerStructure> selectByCustId(Integer custId);

    int deleteByCustId(Integer custId);
}
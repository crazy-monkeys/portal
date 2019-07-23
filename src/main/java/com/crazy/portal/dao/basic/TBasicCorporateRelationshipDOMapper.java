package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.TBasicCorporateRelationshipDO;

public interface TBasicCorporateRelationshipDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBasicCorporateRelationshipDO record);

    int insertSelective(TBasicCorporateRelationshipDO record);

    TBasicCorporateRelationshipDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBasicCorporateRelationshipDO record);

    int updateByPrimaryKey(TBasicCorporateRelationshipDO record);
}
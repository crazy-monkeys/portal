package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.TBasicCorporateStructureDO;

public interface TBasicCorporateStructureDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBasicCorporateStructureDO record);

    int insertSelective(TBasicCorporateStructureDO record);

    TBasicCorporateStructureDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBasicCorporateStructureDO record);

    int updateByPrimaryKey(TBasicCorporateStructureDO record);
}
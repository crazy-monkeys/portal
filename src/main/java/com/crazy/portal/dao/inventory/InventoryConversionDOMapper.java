package com.crazy.portal.dao.inventory;

import com.crazy.portal.entity.inventory.InventoryConversionDO;

public interface InventoryConversionDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(InventoryConversionDO record);

    InventoryConversionDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InventoryConversionDO record);
}
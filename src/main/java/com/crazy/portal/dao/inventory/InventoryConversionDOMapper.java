package com.crazy.portal.dao.inventory;

import com.crazy.portal.entity.inventory.InventoryConversionDO;

import java.util.List;

public interface InventoryConversionDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(InventoryConversionDO record);

    InventoryConversionDO selectByPrimaryKey(Integer id);

    List<InventoryConversionDO> findConversionWithPage(InventoryConversionDO transferDO);

    int updateByPrimaryKeySelective(InventoryConversionDO record);
}
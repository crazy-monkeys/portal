package com.crazy.portal.dao.inventory;

import com.crazy.portal.entity.inventory.InventoryTransferDO;

public interface InventoryTransferDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(InventoryTransferDO record);

    InventoryTransferDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InventoryTransferDO record);
}
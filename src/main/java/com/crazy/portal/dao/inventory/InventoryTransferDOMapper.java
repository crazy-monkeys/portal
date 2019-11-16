package com.crazy.portal.dao.inventory;

import com.crazy.portal.entity.inventory.InventoryTransferDO;

import java.util.List;

public interface InventoryTransferDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(InventoryTransferDO record);

    InventoryTransferDO selectByPrimaryKey(Integer id);

    List<InventoryTransferDO> findTransferWithPage(InventoryTransferDO transferDO);

    int updateByPrimaryKeySelective(InventoryTransferDO record);
}
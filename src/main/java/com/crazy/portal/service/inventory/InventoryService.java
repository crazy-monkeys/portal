package com.crazy.portal.service.inventory;

import com.crazy.portal.dao.inventory.InventoryConversionDOMapper;
import com.crazy.portal.dao.inventory.InventoryTransferDOMapper;
import com.crazy.portal.entity.inventory.InventoryTransferDO;
import com.crazy.portal.entity.inventory.InventoryConversionDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 15:14 2019-11-16
 * @Modified by:
 */
@Service
public class InventoryService {


    @Resource
    private InventoryTransferDOMapper transferDOMapper;
    @Resource
    private InventoryConversionDOMapper conversionDOMapper;

    /**
     * 转移
     * @param userId
     * @param inventoryDO
     * @return
     */
    public boolean transfer(Integer userId, InventoryTransferDO inventoryDO){
        inventoryDO.setCreateId(userId);
        inventoryDO.setCreateTime(new Date());
        transferDOMapper.insertSelective(inventoryDO);
        return true;
    }

    /**
     * 转换
     * @param userId
     * @param conversionDO
     * @return
     */
    public boolean conversion(Integer userId, InventoryConversionDO conversionDO){
        conversionDO.setCreateId(userId);
        conversionDO.setCreateTime(new Date());
        conversionDOMapper.insertSelective(conversionDO);
        return true;
    }
}

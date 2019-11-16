package com.crazy.portal.service.inventory;

import com.crazy.portal.dao.inventory.InventoryConversionDOMapper;
import com.crazy.portal.dao.inventory.InventoryTransferDOMapper;
import com.crazy.portal.entity.inventory.InventoryConversionDO;
import com.crazy.portal.entity.inventory.InventoryTransferDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
     * @param transferDOS
     * @return
     */
    public boolean transfer(Integer userId, List<InventoryTransferDO> transferDOS){
        transferDOS.forEach(x->{
            x.setId(null);
            x.setCreateId(userId);
            x.setCreateTime(new Date());
            transferDOMapper.insertSelective(x);
        });
        return true;
    }

    /**
     * 转换
     * @param userId
     * @param conversionDOS
     * @return
     */
    public boolean conversion(Integer userId, List<InventoryConversionDO> conversionDOS){
        conversionDOS.forEach(x->{
            x.setId(null);
            x.setCreateId(userId);
            x.setCreateTime(new Date());
            conversionDOMapper.insertSelective(x);
        });
        return true;
    }
}

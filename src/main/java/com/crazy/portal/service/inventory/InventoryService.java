package com.crazy.portal.service.inventory;

import com.crazy.portal.dao.inventory.InventoryConversionDOMapper;
import com.crazy.portal.dao.inventory.InventoryTransferDOMapper;
import com.crazy.portal.entity.inventory.InventoryConversionDO;
import com.crazy.portal.entity.inventory.InventoryTransferDO;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.PageInfo;
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
     * @param user
     * @param transferDOS
     * @return
     */
    public boolean transfer(User user, List<InventoryTransferDO> transferDOS){
        transferDOS.forEach(x->{
            x.setId(null);
            x.setCreateId(user.getId());
            x.setCreateTime(new Date());
            x.setApprovalStatus(0);
            x.setCreateUserName(user.getLoginName());
            transferDOMapper.insertSelective(x);
        });
        return true;
    }

    /**
     * 转移申请列表
     * @param transferDO
     * @return
     */
    public PageInfo<InventoryTransferDO> transferApplyList(InventoryTransferDO transferDO){
        PortalUtil.defaultStartPage(transferDO.getPageIndex(), transferDO.getPageSize());
        List<InventoryTransferDO> list = transferDOMapper.findTransferWithPage(transferDO);
        list.forEach(x->x.setApplyType("transfer"));
        return new PageInfo<>(list);
    }

    /**
     * 转移审批
     * @param userid
     * @param id
     * @param approvalStatus
     * @return
     */
    public boolean transferApproval(Integer userid, Integer id, Integer approvalStatus){
        InventoryTransferDO inventoryTransferDO = transferDOMapper.selectByPrimaryKey(id);
        BusinessUtil.notNull(inventoryTransferDO, ErrorCodes.InventoryEnum.INVENTORY_TRANSFER_NOT_EXISTS);

        BusinessUtil.assertTrue(inventoryTransferDO.getApprovalStatus().equals(0),ErrorCodes.InventoryEnum.INVENTORY_BEEN_APPROVAL);
        inventoryTransferDO.setApprovalStatus(approvalStatus);
        inventoryTransferDO.setUpdateId(userid);
        inventoryTransferDO.setUpdateTime(new Date());
        transferDOMapper.updateByPrimaryKeySelective(inventoryTransferDO);
        return true;
    }

    /**
     * 转换
     * @param user
     * @param conversionDOS
     * @return
     */
    public boolean conversion(User user, List<InventoryConversionDO> conversionDOS){
        conversionDOS.forEach(x->{
            x.setId(null);
            x.setCreateId(user.getId());
            x.setCreateUserName(user.getLoginName());
            x.setCreateTime(new Date());
            x.setApprovalStatus(0);
            conversionDOMapper.insertSelective(x);
        });
        return true;
    }

    /**
     * 转换申请列表
     * @param conversionDO
     * @return
     */
    public PageInfo<InventoryConversionDO> conversionApplyList(InventoryConversionDO conversionDO){
        PortalUtil.defaultStartPage(conversionDO.getPageIndex(), conversionDO.getPageSize());
        List<InventoryConversionDO> list = conversionDOMapper.findConversionWithPage(conversionDO);
        list.forEach(x->x.setApplyType("conversion"));
        return new PageInfo<>(list);
    }

    /**
     * 转换审批
     * @param userid
     * @param id
     * @param approvalStatus
     * @return
     */
    public boolean conversionApproval(Integer userid, Integer id, Integer approvalStatus){
        InventoryConversionDO inventoryConversionDO = conversionDOMapper.selectByPrimaryKey(id);
        BusinessUtil.notNull(inventoryConversionDO, ErrorCodes.InventoryEnum.INVENTORY_CONVERSION_NOT_EXISTS);

        inventoryConversionDO.setApprovalStatus(approvalStatus);
        inventoryConversionDO.setUpdateId(userid);
        inventoryConversionDO.setUpdateTime(new Date());
        conversionDOMapper.updateByPrimaryKeySelective(inventoryConversionDO);
        return true;
    }
}

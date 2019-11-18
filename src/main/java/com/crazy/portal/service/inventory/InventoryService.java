package com.crazy.portal.service.inventory;

import com.crazy.portal.dao.inventory.InventoryConversionDOMapper;
import com.crazy.portal.dao.inventory.InventoryTransferDOMapper;
import com.crazy.portal.entity.handover.ReceiveDetail;
import com.crazy.portal.entity.inventory.InventoryConversionDO;
import com.crazy.portal.entity.inventory.InventoryTransferDO;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.service.handover.ReceiveService;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.DateUtil;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    @Resource
    private ReceiveService receiveService;

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
        boolean flg = receiveService.pushReceiveDataToBi(mappingInventoryReceive(inventoryTransferDO), inventoryTransferDO.getCreateId());
        if(flg){
            inventoryTransferDO.setApprovalStatus(approvalStatus);
            inventoryTransferDO.setUpdateId(userid);
            inventoryTransferDO.setUpdateTime(new Date());
            transferDOMapper.updateByPrimaryKeySelective(inventoryTransferDO);
            return true;
        }
        return false;
    }

    //封装转移数据
    private List<ReceiveDetail> mappingInventoryReceive(InventoryTransferDO inventoryTransferDO){
        List<ReceiveDetail> list = new ArrayList<>();

        //转出记录
        list.add(getInventoryRecive(inventoryTransferDO,
                String.valueOf(0 - inventoryTransferDO.getAgencyPickUpQty()),
                inventoryTransferDO.getTransferIntoCustomer()));

        //转入记录
        list.add(getInventoryRecive(inventoryTransferDO,
                String.valueOf(inventoryTransferDO.getAgencyPickUpQty()),
                inventoryTransferDO.getTransferIntoCustomer()));

        return list;
    }

    private ReceiveDetail getInventoryRecive(InventoryTransferDO inventoryTransferDO, String qty, String custCode){
        ReceiveDetail detail = new ReceiveDetail();
        detail.setDealerName(inventoryTransferDO.getCreateUserName());
        detail.setCustomerType(inventoryTransferDO.getCustomerType());
        detail.setProductModel(inventoryTransferDO.getProduct());
        detail.setPlatform(inventoryTransferDO.getClass3());
        detail.setInventoryCategory(inventoryTransferDO.getInventoryType());
        detail.setSalesOrganization(inventoryTransferDO.getSalesOrg());
        detail.setDeliveryTime(inventoryTransferDO.getAgencyPickUpDate());
        detail.setDeliveryCompany(inventoryTransferDO.getCompany());
        detail.setInventoryUnitPrice(inventoryTransferDO.getInventoryPrice());
        detail.setIsTransfer("1");
        detail.setStockTransferYearMonth(inventoryTransferDO.getTransferYearMonth());

        detail.setDeliveryNum(qty);
        detail.setInsideCustomerId(custCode);
        return detail;
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

        boolean flg = receiveService.pushReceiveDataToBi(mappingConversionReceive(inventoryConversionDO), inventoryConversionDO.getCreateId());
        if(flg){
            inventoryConversionDO.setApprovalStatus(approvalStatus);
            inventoryConversionDO.setUpdateId(userid);
            inventoryConversionDO.setUpdateTime(new Date());
            conversionDOMapper.updateByPrimaryKeySelective(inventoryConversionDO);
            return true;
        }
        return false;
    }

    //封装转换数据
    private List<ReceiveDetail> mappingConversionReceive(InventoryConversionDO inventoryConversionDO){
        List<ReceiveDetail> list = new ArrayList<>();

        //转出记录
        list.add(getConversionRecive(inventoryConversionDO,
                String.valueOf(0 - inventoryConversionDO.getAgencyPickUpQty()),
                inventoryConversionDO.getInventoryType().equals("客户专货")?inventoryConversionDO.getCustCode():"",
                inventoryConversionDO.getCustomerType(),
                inventoryConversionDO.getInventoryType()));

        //转入记录
        list.add(getConversionRecive(inventoryConversionDO,
                String.valueOf(inventoryConversionDO.getAgencyPickUpQty()),
                inventoryConversionDO.getConversionInventoryType().equals("客户专货")?inventoryConversionDO.getCustCode():"",
                inventoryConversionDO.getConversionCustomerType(),
                inventoryConversionDO.getConversionInventoryType()));

        return list;
    }

    private ReceiveDetail getConversionRecive(InventoryConversionDO inventoryConversionDO, String qty, String custCode, String custType, String inventoryType){
        ReceiveDetail detail = new ReceiveDetail();
        detail.setDealerName(inventoryConversionDO.getCreateUserName());
        detail.setProductModel(inventoryConversionDO.getProduct());
        detail.setPlatform(inventoryConversionDO.getClass3());
        detail.setSalesOrganization(inventoryConversionDO.getSalesOrg());
        detail.setDeliveryTime(inventoryConversionDO.getAgencyPickUpDate());
        detail.setDeliveryCompany(inventoryConversionDO.getCompany());
        detail.setInventoryUnitPrice(inventoryConversionDO.getInventoryPrice());
        detail.setIsTransfer("1");
        detail.setStockTransferYearMonth(inventoryConversionDO.getConversionYearMonth());

        detail.setDeliveryNum(qty);
        detail.setInsideCustomerId(custCode);
        detail.setCustomerType(custType);
        detail.setInventoryCategory(inventoryType);
        return detail;
    }
}

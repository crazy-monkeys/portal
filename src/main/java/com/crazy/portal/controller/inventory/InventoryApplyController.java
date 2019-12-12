package com.crazy.portal.controller.inventory;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.dao.cusotmer.CustomerInfoMapper;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.entity.inventory.InventoryConversionDO;
import com.crazy.portal.entity.inventory.InventoryTransferDO;
import com.crazy.portal.service.inventory.InventoryService;
import com.crazy.portal.service.system.UserCustomerMappingService;
import com.crazy.portal.util.Enums;
import com.crazy.portal.util.StringUtil;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 18:13 2019-11-16
 * @Modified by:
 */
@RestController
@RequestMapping("/inventoryApply")
public class InventoryApplyController extends BaseController {

    @Resource
    private InventoryService inventoryService;
    @Resource
    private CustomerInfoMapper customerInfoMapper;
    @Resource
    private UserCustomerMappingService userCustomerMappingService;

    @Data
    public static class InventoryBean{
        private String applyType;
        private InventoryTransferDO transfer;
        private InventoryConversionDO conversion;
    }

    @PostMapping("/list")
    public BaseResponse transferList(@RequestBody InventoryBean inventoryBean){
        String applyType = inventoryBean.getApplyType();
        List<Integer> userList = new ArrayList<>();
        String abbreviation = "";

        if(StringUtil.isEmpty(applyType)){
            applyType = "transfer";
        }

        if(!super.getCurrentUser().getUserType().equals(Enums.USER_TYPE.internal.toString())){
            CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(super.getCurrentUser().getDealerId());
            abbreviation = customerInfo.getCustAbbreviation();
        }else if(super.getCurrentUser().getUserType().equals(Enums.USER_TYPE.internal.toString())){
            userList = userCustomerMappingService.selectUserMapping(super.getCurrentUser().getId(), Enums.CustomerMappingModel.Inventory.getValue());
            Integer[] userIds = new Integer[userList.size()];
            userList.toArray(userIds);
        }

        if(applyType.equals("transfer")){
            InventoryTransferDO transferDO = inventoryBean.getTransfer();
            transferDO.setAgencyShortName(abbreviation);
            transferDO.setUserIds(userList);
            return super.successResult(inventoryService.transferApplyList(transferDO));
        }else if(applyType.equals("conversion")){
            InventoryConversionDO conversionDO = inventoryBean.getConversion();
            conversionDO.setAgencyShortName(abbreviation);
            conversionDO.setUserIds(userList);
            return super.successResult(inventoryService.conversionApplyList(conversionDO));
        }
        return new BaseResponse();
    }
}

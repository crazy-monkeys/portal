package com.crazy.portal.controller.inventory;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.inventory.InventoryConversionDO;
import com.crazy.portal.entity.inventory.InventoryTransferDO;
import com.crazy.portal.service.inventory.InventoryService;
import com.crazy.portal.util.StringUtil;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @Data
    public static class InventoryBean{
        private String applyType;
        private InventoryTransferDO transfer;
        private InventoryConversionDO conversion;
    }

    @PostMapping("/list")
    public BaseResponse transferList(@RequestBody InventoryBean inventoryBean){
        String applyType = inventoryBean.getApplyType();
        if(StringUtil.isEmpty(applyType)){
            applyType = "transfer";
        }
        if(applyType.equals("transfer")){
            return super.successResult(inventoryService.transferApplyList(inventoryBean.getTransfer()));
        }else if(applyType.equals("conversion")){
            return super.successResult(inventoryService.conversionApplyList(inventoryBean.getConversion()));
        }
        return new BaseResponse();
    }
}

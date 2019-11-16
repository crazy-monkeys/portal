package com.crazy.portal.controller.inventory;

import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.inventory.InventoryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 17:59 2019-11-16
 * @Modified by:
 */
@RestController
@RequestMapping("/inventoryApproval")
public class InventoryApprovalController extends BaseController {

    @Resource
    private InventoryService inventoryService;

    @PostMapping("/{applyType}/{approvalStatus}/{id}")
    @OperationLog
    public BaseResponse transferApproval(@PathVariable String applyType,
                                         @PathVariable Integer approvalStatus,
                                         @PathVariable Integer id){

        if(applyType.equals("transfer")){
            return super.successResult(inventoryService.transferApproval(super.getCurrentUserId(),id,approvalStatus));
        }else if(applyType.equals("conversion")){
            return super.successResult(inventoryService.conversionApproval(super.getCurrentUserId(),id,approvalStatus));
        }
        return new BaseResponse();
    }
}

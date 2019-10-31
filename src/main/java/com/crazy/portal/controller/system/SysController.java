package com.crazy.portal.controller.system;

import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.customer.basic.CustFileUploadVO;
import com.crazy.portal.bean.customer.basic.UploadFileVO;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.dao.system.InternalUserMapper;
import com.crazy.portal.service.customer.CustomerInfoService;
import com.crazy.portal.service.group.SalesGroupService;
import com.crazy.portal.service.system.SysParamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * @ClassName: SysCustomerController
 * @Author: God Man Qiu~
 * @Date: 2019/9/9 15:05
 */
@Slf4j
@RestController
@RequestMapping("/sys")
public class SysController extends BaseController {
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private SysParamService sysParamService;
    @Resource
    private SalesGroupService salesGroupService;
    @Resource
    private InternalUserMapper internalUserMapper;

    @GetMapping("/customer/all")
    public BaseResponse getAllCustomer(){
        return successResult(customerInfoService.selecAllCustomer());
    }

    /**
     * 获取销售
     * @return
     */
    @GetMapping("/sales/list")
    public BaseResponse getAllSales(){
        return successResult(internalUserMapper.selectSales());
    }

    /**
     * 获取cs
     * @return
     */
    @GetMapping("/cs/list")
    public BaseResponse getAllCS(){
        return successResult(internalUserMapper.selectCS());
    }

    /**
     * 获取代理商经营部
     * @return
     */
    @GetMapping("/ds/list")
    public BaseResponse getAllDS(){
        return successResult(internalUserMapper.selectDS());
    }

    /**
     * 获取代理商
     * @return
     */
    @GetMapping("/dealer/list")
    public BaseResponse getDealerList(){
        return successResult(customerInfoService.getDealerList());
    }

    @GetMapping("/dealer/getCredit/{dealerId}")
    public BaseResponse getDealerCredit(@PathVariable("dealerId") Integer dealerId){
        return successResult(customerInfoService.getDealerCredit(dealerId));
    }

    @GetMapping("/selectCustomerShip/{custId}")
    public BaseResponse selectCustomerShip(@PathVariable("custId") Integer custId){
        return super.successResult(customerInfoService.selectCustShip(custId));
    }

    @GetMapping("/selectByMAndF/{model}/{function}")
    public BaseResponse selectByMAndF(@PathVariable("model") String model, @PathVariable("function") String function){
        return super.successResult(sysParamService.selectParam(model, function));
    }

    /**
     * 获取销售组织
     * @return
     */
    @GetMapping(value = "/list")
    public BaseResponse getList(){
        return successResult(salesGroupService.selectSalesGroup());
    }

    @OperationLog
    @PostMapping(value = "/customer/file/upload")
    public BaseResponse customerFileUpload(CustFileUploadVO vo){
        customerInfoService.uploadCustomerFiles(vo);
        return successResult();
    }

    @GetMapping(value = "/customer/file/list/{outCode}")
    public BaseResponse customerFileUpload(@PathVariable String outCode){
        return successResult(customerInfoService.selectCustomerFile(outCode));
    }
}

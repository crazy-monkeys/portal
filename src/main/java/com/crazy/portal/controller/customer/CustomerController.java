package com.crazy.portal.controller.customer;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.customer.CustomerQueryBean;
import com.crazy.portal.bean.customer.approval.ApprovalBean;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.cusotmer.AssetsInformation;
import com.crazy.portal.entity.cusotmer.BusinessInformation;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.service.customer.CustomerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 客户管理
 * @Author Shawn
 * @Date 2019-07-11 22:00
 * @Modify by
 */
@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController extends BaseController{
    @Resource
    private CustomerInfoService customerInfoService;

    /**
     * 获取Dealer的个人信息
     * 取当前用户
     * @return
     */
    @GetMapping("/getDealerInfo")
    public BaseResponse getDealerInfo(){
        return super.successResult(customerInfoService.getDealerInfo(this.getCurrentUser().getDealerId()));
    }

    /**
     * 修改Dealer信息
     */
    @PostMapping("/updateDealerInfo")
    public BaseResponse updateDealerInfo(@RequestBody CustomerInfo vo){
        customerInfoService.updateCustomerInfo(vo, this.getCurrentUser().getId());
        return successResult();
    }

    //校验客户是否可报备
    @GetMapping(value = "/check/{customerName}")
    public BaseResponse checkCustomer(@PathVariable String customerName){
        return successResult(customerInfoService.checkCustomerReport(customerName, getCurrentUser()));
    }

    //报备客户
    @PostMapping(value = "/report")
    public BaseResponse reportCustomer(@RequestBody CustomerInfo customerInfo){
        customerInfoService.reportCustomer(customerInfo, getCurrentUser());
        return successResult();
    }

    //审批
    @PostMapping("/approval")
    public BaseResponse approval(@RequestBody ApprovalBean bean){
        bean.setApproveUser(this.getCurrentUser().getId());
        customerInfoService.approval(bean);
        return successResult();
    }

    //查询所有客户
    @PostMapping("/list")
    public BaseResponse list(@RequestBody CustomerQueryBean customerQueryBean){
        return successResult(customerInfoService.queryList(customerQueryBean));
    }

    //查询客户明细
    @GetMapping("/info/{reportId}/{custId}")
    public BaseResponse info(@PathVariable Integer reportId, @PathVariable Integer custId){
        return successResult(customerInfoService.queryInfo(reportId,custId));
    }
/**
    //获取用户列表
    @GetMapping("/list")
    public BaseResponse customers(CustomerQueryBean bean){
        bean.setUserId(this.getCurrentUser().getId());
        PageInfo page = customersService.queryCustByPage(bean);
        return successResult(page);
    }

    //获取用户明细信息
    @GetMapping(value="/info/{id}")
    public BaseResponse getCustomer(@PathVariable Integer id){
        return successResult(customersService.queryCustDetail(id));
    }

    //校验客户是否可报备
    @GetMapping(value = "/check/{customerName}")
    public BaseResponse checkCustomer(@PathVariable String customerName){
        return successResult(customersService.checkCustomerReport(customerName,getCurrentUser()));
    }

    //报备客户
    @PostMapping("/customer/report")
    public BaseResponse customerReport(CustomerInfo vo){

        return successResult();
    }

    //创建用户信息
    @PostMapping("/info")
    public BaseResponse customerAdd(CustomerInfo vo){
        customersService.addOrUpdate(vo, this.getCurrentUser());
        return successResult();
    }

    //删除用户信息
    @DeleteMapping("/info/{id}")
    public BaseResponse customerDelete(@PathVariable Integer id){
        customersService.delete(id, this.getCurrentUser().getId());
        return successResult();
    }

    //报备
    @GetMapping("/againReport/{id}")
    public BaseResponse againReport(@PathVariable Integer id){
        customersService.againReport(id, this.getCurrentUser().getId());
        return successResult();
    }

    //审批
    @GetMapping("/approval")
    public BaseResponse approval(ApprovalBean bean){
        bean.setUserId(this.getCurrentUser().getId());
        customersService.approval(bean);
        return successResult();
    }

    //驳回
    @GetMapping("/reject")
    public BaseResponse reject(ApprovalBean bean){
        bean.setUserId(this.getCurrentUser().getId());
        customersService.reject(bean);
        return successResult();
    }

    @GetMapping("/checkName")
    public BaseResponse checkName(String custName){
        customersService.checkCustName(custName);
        return successResult();
    }

    @GetMapping("/visitRecord/list")
    public BaseResponse visitRecordList(VisitRecordQueryBean bean){
        bean.setUserId(this.getCurrentUser().getId());
        return successResult(customersService.selectVisitRecordPage(bean));
    }

    @GetMapping("/visitRecord/download")
    public void visitRecordDownload(HttpServletResponse response){
        try {
            Map<String, List<? extends BaseRowModel>> resultMap = customersService.downloadTemplate(this.getCurrentUser().getId());
            ExcelUtils.createExcelStreamMutilByEaysExcel(response, resultMap, "拜访记录", ExcelTypeEnum.XLSX);
        }catch (Exception ex){
            log.error("下载模板异常", ex);
        }
    }

    @PostMapping("/visitRecord/upload")
    public BaseResponse visitRecordUpload(MultipartFile[] files) throws Exception{
        customersService.uploadVisitRecord(files, this.getCurrentUser().getId());
        return successResult();
    }

    @PostMapping("/file")
    public BaseResponse fileUpload(MultipartFile[] files){
        return successResult(customersService.fileUpload(files));
    }

    @GetMapping("/file/{id}")
    public void fileDownload(HttpServletResponse response, @PathVariable Integer id){
        customersService.fileDownload(response, id);
    }

    @GetMapping("/all")
    public BaseResponse queryAllCustomer(){
        return successResult(customersService.queryAllCustomer());
    }
    }*/
}

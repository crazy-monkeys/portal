package com.crazy.portal.controller.customer;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.customer.CustomerQueryBean;
import com.crazy.portal.bean.customer.approval.ApprovalBean;
import com.crazy.portal.bean.customer.visitRecord.VisiApproveBean;
import com.crazy.portal.bean.customer.visitRecord.VisitRecordQueryBean;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.dao.system.InternalUserMapper;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.entity.system.InternalUser;
import com.crazy.portal.service.customer.CustomerInfoService;
import com.crazy.portal.util.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
    private InternalUserMapper internalUserMapper;
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
     * //TODO 季度年度更新提醒
     */
    @PostMapping("/updateDealerInfo")
    public BaseResponse updateDealerInfo(@RequestBody CustomerInfo vo){
        customerInfoService.updateDealerInfo(vo, this.getCurrentUser().getId());
        return successResult();
    }

    @PostMapping("/file")
    public BaseResponse fileUpload(MultipartFile file, Integer index){
        return successResult(customerInfoService.fileUpload(file, getCurrentUserId(), index));
    }

    //校验客户是否可报备
    @GetMapping(value = "/check/{customerName}")
    public BaseResponse checkCustomer(@PathVariable String customerName){
        return successResult(customerInfoService.checkCustomerReport(customerName, getCurrentUser()));
    }

    //报备客户
    @PostMapping(value = "/report")
    public BaseResponse reportCustomer(@Valid CustomerInfo customerInfo){
        customerInfoService.report(customerInfo, getCurrentUser());
        return successResult();
    }

    //审批 通过
    @GetMapping("/approval")
    public BaseResponse approval(ApprovalBean bean){
        bean.setApproveUser(this.getCurrentUser().getId());
        customerInfoService.approval(bean, getCurrentUserId());
        return successResult();
    }

    //审批驳回
    @GetMapping("/reject")
    public BaseResponse reject(ApprovalBean bean){
        bean.setApproveUser(this.getCurrentUser().getId());
        customerInfoService.approval(bean, getCurrentUserId());
        return successResult();
    }

    //查询所有客户
    @GetMapping("/list")
    public BaseResponse list(CustomerQueryBean customerQueryBean){
        InternalUser internalUser = internalUserMapper.selectUserByName(super.getCurrentUser().getLoginName());
        return successResult(customerInfoService.queryList(customerQueryBean, getCurrentUser(),internalUser));
    }

    //查询客户明细
    @GetMapping("/info/{custId}")
    public BaseResponse info(@PathVariable Integer custId){
        return successResult(customerInfoService.queryInfo(custId));
    }

    //删除客户
    @GetMapping("/delete/{custId}")
    public BaseResponse delete(@PathVariable Integer custId){
        customerInfoService.deleteCustomer(custId);
        return successResult();
    }

    @PostMapping(value = "/update")
    public BaseResponse update(CustomerInfo customerInfo){
        customerInfoService.updateCustomerInfo(customerInfo, getCurrentUser());
        return successResult();
    }

    @GetMapping("/visitRecord/list")
    public BaseResponse visitRecordList(VisitRecordQueryBean bean){
        bean.setUserId(this.getCurrentUser().getId());
        return successResult(customerInfoService.selectVisitRecordPage(bean));
    }

    @GetMapping("/visitRecord/download")
    public void visitRecordDownload(HttpServletResponse response){
        try {
            Map<String, List<? extends BaseRowModel>> resultMap = customerInfoService.downloadTemplate(this.getCurrentUser().getId());
            ExcelUtils.createExcelStreamMutilByEaysExcel(response, resultMap, "拜访记录", ExcelTypeEnum.XLSX);
        }catch (Exception ex){
            log.error("下载模板异常", ex);
        }
    }

    @PostMapping("/visitRecord/upload")
    public BaseResponse visitRecordUpload(MultipartFile[] files) throws Exception{
        return successResult(customerInfoService.uploadVisitRecord(files, this.getCurrentUser().getId()));
    }

    @PostMapping("/visitRecord/approve")
    public BaseResponse approve(@RequestBody VisiApproveBean ids){
        customerInfoService.approve(ids.getIds(),super.getCurrentUser().getDealerId());
        return successResult();
    }

    @GetMapping("/all")
    public BaseResponse queryAllCustomer(){
        return successResult(customerInfoService.selecAllCustomer());
    }

    @GetMapping("/getShipp")
    public BaseResponse getShip(){
        return successResult();
    }
}
